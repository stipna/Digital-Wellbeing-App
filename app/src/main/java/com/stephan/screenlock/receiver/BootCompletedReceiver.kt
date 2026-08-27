package com.stephan.screenlock.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.stephan.screenlock.service.WellbeingForegroundService
import com.stephan.screenlock.util.AccessibilityUtils

class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_BOOT_COMPLETED) return
        // Nur starten wenn die Bedienungshilfe zu diesem Zeitpunkt bereits
        // aktiv ist — vermeidet Fehlalarm bei langsamem System-Hochlauf.
        if (AccessibilityUtils.isAppLockServiceEnabled(context)) {
            WellbeingForegroundService.start(context)
        }
    }
}
