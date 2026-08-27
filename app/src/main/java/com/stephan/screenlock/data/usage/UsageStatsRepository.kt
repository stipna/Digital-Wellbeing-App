package com.stephan.screenlock.data.usage

import android.app.AppOpsManager
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.Process
import android.provider.Settings
import java.util.Calendar

/**
 * Berechnet die kumulierte Vordergrund-Nutzungsdauer seit Mitternacht frisch
 * bei jedem Aufruf. Kein persistenter Zaehler noetig (naechster Tag = neues
 * Query-Fenster automatisch). Siehe claude/project-setup.md.
 */
class UsageStatsRepository(private val context: Context) {

    fun getTodayUsageMinutes(packageName: String): Long {
        val usageStatsManager =
            context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val startOfDay = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
        val now = System.currentTimeMillis()

        val events = usageStatsManager.queryEvents(startOfDay, now)
        var totalMillis = 0L
        var foregroundSince: Long? = null
        val event = UsageEvents.Event()

        while (events.hasNextEvent()) {
            events.getNextEvent(event)
            if (event.packageName != packageName) continue
            when (event.eventType) {
                UsageEvents.Event.MOVE_TO_FOREGROUND -> foregroundSince = event.timeStamp
                UsageEvents.Event.MOVE_TO_BACKGROUND -> {
                    val since = foregroundSince
                    if (since != null) {
                        totalMillis += event.timeStamp - since
                        foregroundSince = null
                    }
                }
            }
        }
        // App laeuft aktuell noch im Vordergrund: Spanne bis "jetzt" mitzaehlen.
        foregroundSince?.let { totalMillis += now - it }

        return totalMillis / 60000
    }

    fun hasUsageAccess(): Boolean {
        val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.unsafeCheckOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            Process.myUid(),
            context.packageName
        )
        return mode == AppOpsManager.MODE_ALLOWED
    }

    fun usageAccessSettingsIntent(): Intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
}
