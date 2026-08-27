package com.stephan.screenlock.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import com.stephan.screenlock.util.AccessibilityUtils
import com.stephan.screenlock.util.NotificationHelper

/**
 * START_STICKY Foreground Service. Handler-Loop prueft, ob die
 * Bedienungshilfe noch aktiv ist, und feuert bei Uebergang aktiv->inaktiv
 * einmalig einen Hinweis. Siehe claude/project-setup.md.
 */
class WellbeingForegroundService : Service() {

    private val handler = Handler(Looper.getMainLooper())
    private var lastKnownEnabled: Boolean? = null
    private var isFirstCheck = true

    private val watchdog = object : Runnable {
        override fun run() {
            val enabled = AccessibilityUtils.isAppLockServiceEnabled(applicationContext)
            if (isFirstCheck) {
                isFirstCheck = false
                lastKnownEnabled = enabled
            } else if (lastKnownEnabled == true && !enabled) {
                NotificationHelper.showReenableHint(applicationContext)
                lastKnownEnabled = enabled
            } else if (lastKnownEnabled == false && enabled) {
                NotificationHelper.clearReenableHint(applicationContext)
                lastKnownEnabled = enabled
            }
            handler.postDelayed(this, WATCHDOG_INTERVAL_MS)
        }
    }

    override fun onCreate() {
        super.onCreate()
        NotificationHelper.ensureChannels(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(FOREGROUND_NOTIFICATION_ID, NotificationHelper.buildForegroundNotification(this))
        handler.post(watchdog)
        return START_STICKY
    }

    override fun onDestroy() {
        handler.removeCallbacks(watchdog)
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        private const val WATCHDOG_INTERVAL_MS = 15_000L
        private const val FOREGROUND_NOTIFICATION_ID = 2001

        fun start(context: Context) {
            context.startForegroundService(Intent(context, WellbeingForegroundService::class.java))
        }
    }
}
