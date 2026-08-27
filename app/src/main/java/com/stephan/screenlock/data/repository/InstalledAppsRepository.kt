package com.stephan.screenlock.data.repository

import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import androidx.core.graphics.drawable.toBitmap
import com.stephan.screenlock.domain.model.InstalledApp

/**
 * Query aller Launcher-Apps via PackageManager.queryIntentActivities
 * (ACTION_MAIN / CATEGORY_LAUNCHER). Erfordert das <queries>-Element im
 * Manifest (bereits enthalten), sonst leere Liste ab targetSdk 30.
 */
class InstalledAppsRepository(private val context: Context) {

    fun getInstalledApps(): List<InstalledApp> {
        val pm = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN, null).addCategory(Intent.CATEGORY_LAUNCHER)
        return pm.queryIntentActivities(intent, 0)
            .asSequence()
            .map { it.activityInfo }
            .filter { it.packageName != context.packageName }
            .distinctBy { it.packageName }
            .map { info ->
                val drawable = info.loadIcon(pm)
                val bitmap = (drawable as? BitmapDrawable)?.bitmap ?: drawable.toBitmap(40, 40)
                InstalledApp(
                    packageName = info.packageName,
                    label = info.loadLabel(pm).toString(),
                    icon = bitmap
                )
            }
            .sortedBy { it.label.lowercase() }
            .toList()
    }
}
