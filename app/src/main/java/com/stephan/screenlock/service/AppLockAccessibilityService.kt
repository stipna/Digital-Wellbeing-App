package com.stephan.screenlock.service

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent
import com.stephan.screenlock.ui.screens.lock.BlockedAppActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob

/**
 * Faengt TYPE_WINDOW_STATE_CHANGED ab und wertet zwei unabhaengige Regeln
 * aus: binaere Sperr-Liste (sofortige Sperre) und Zeitlimit (Sperre erst bei
 * Erreichen des Limits, 30s-Polling waehrend die App im Vordergrund bleibt).
 * Siehe claude/project-setup.md.
 *
 * TODO: Flow-Abos auf BlockedAppDao/AppLimitDao + Volatile-Caches gemaess
 * project-setup.md einbauen; onServiceConnected()/onUnbind() Overrides fuer
 * den Foreground-Service-Watchdog noch einzubauen.
 */
class AppLockAccessibilityService : AccessibilityService() {

    private val serviceScope = CoroutineScope(SupervisorJob())
    private var limitWatchJob: Job? = null
    private var foregroundLimitedPackage: String? = null

    @Volatile private var blockedPackages: Set<String> = emptySet()
    @Volatile private var limitedPackages: Map<String, Int> = emptyMap()

    override fun onServiceConnected() {
        super.onServiceConnected()
        WellbeingForegroundService.start(this)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) return
        val packageName = event.packageName?.toString() ?: return

        if (packageName in blockedPackages) {
            launchBlockScreen(packageName)
            return
        }
        // TODO: Zeitlimit-Pruefung + 30s-Polling-Job (siehe project-setup.md).
    }

    private fun launchBlockScreen(packageName: String) {
        val intent = Intent(this, BlockedAppActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            putExtra(BlockedAppActivity.EXTRA_PACKAGE_NAME, packageName)
        }
        startActivity(intent)
    }

    override fun onInterrupt() = Unit

    override fun onUnbind(intent: Intent?): Boolean {
        limitWatchJob?.cancel()
        return super.onUnbind(intent)
    }
}
