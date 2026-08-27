package com.stephan.screenlock.util

/**
 * Reine, kontextfreie Zeitlogik fuer den 7-Tage-Trial.
 * Siehe claude/project-setup.md, Abschnitt "7-Tage-Trial-Gating am App-Einstiegspunkt".
 */
object TrialStatus {
    const val TRIAL_DURATION_DAYS = 7
    private const val DAY_MILLIS = 24L * 60 * 60 * 1000

    fun isTrialExpired(firstInstallTimestampMillis: Long): Boolean {
        if (firstInstallTimestampMillis <= 0) return false
        val elapsed = System.currentTimeMillis() - firstInstallTimestampMillis
        return elapsed >= TRIAL_DURATION_DAYS * DAY_MILLIS
    }

    fun remainingTrialDays(firstInstallTimestampMillis: Long): Int {
        if (firstInstallTimestampMillis <= 0) return TRIAL_DURATION_DAYS
        val elapsedDays = (System.currentTimeMillis() - firstInstallTimestampMillis) / DAY_MILLIS
        return (TRIAL_DURATION_DAYS - elapsedDays).toInt().coerceAtLeast(0)
    }
}
