package com.stephan.screenlock.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

object NotificationHelper {
    const val CHANNEL_FOREGROUND = "wellbeing_foreground"
    const val CHANNEL_ALERTS = "wellbeing_alerts"
    private const val REENABLE_HINT_ID = 1001

    fun ensureChannels(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(
            NotificationChannel(CHANNEL_FOREGROUND, "Wellbeing aktiv", NotificationManager.IMPORTANCE_LOW)
        )
        manager.createNotificationChannel(
            NotificationChannel(CHANNEL_ALERTS, "Wellbeing Hinweise", NotificationManager.IMPORTANCE_HIGH)
        )
    }

    // TODO: echtes Icon statt Platzhalter ic_notification_shield (siehe claude/project-setup.md).
    fun buildForegroundNotification(context: Context): Notification =
        Notification.Builder(context, CHANNEL_FOREGROUND)
            .setContentTitle("Digital Wellbeing aktiv")
            .setOngoing(true)
            .build()

    fun showReenableHint(context: Context) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = Notification.Builder(context, CHANNEL_ALERTS)
            .setContentTitle("Bedienungshilfe deaktiviert")
            .setContentText("Bitte die App-Sperre in den Einstellungen wieder aktivieren.")
            .build()
        manager.notify(REENABLE_HINT_ID, notification)
    }

    fun clearReenableHint(context: Context) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.cancel(REENABLE_HINT_ID)
    }
}
