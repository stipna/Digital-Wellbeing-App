package com.stephan.screenlock.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

/**
 * DataStore-Wrapper fuer alle App-Einstellungen. Konsolidiert gemaess
 * claude/project-setup.md: Countdown/Motivation/Onboarding/Streak +
 * Trial/Billing (firstInstallTimestamp statt trialStartTimestamp).
 *
 * TODO: Motivations-Text-Liste (editierbar) noch nicht implementiert.
 */
class SettingsRepository(private val context: Context) {

    private object Keys {
        val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
        val COUNTDOWN_SECONDS = intPreferencesKey("countdown_seconds")
        val BEST_STREAK_DAYS = intPreferencesKey("best_streak_days")
        val FIRST_INSTALL_TIMESTAMP = longPreferencesKey("first_install_timestamp")
        val LIFETIME_UNLOCKED = booleanPreferencesKey("lifetime_unlocked")
    }

    val onboardingCompleted: Flow<Boolean> =
        context.dataStore.data.map { it[Keys.ONBOARDING_COMPLETED] ?: false }

    suspend fun setOnboardingCompleted(completed: Boolean) {
        context.dataStore.edit { it[Keys.ONBOARDING_COMPLETED] = completed }
    }

    val countdownSeconds: Flow<Int> =
        context.dataStore.data.map { it[Keys.COUNTDOWN_SECONDS] ?: DEFAULT_COUNTDOWN_SECONDS }

    val bestStreakDays: Flow<Int> =
        context.dataStore.data.map { it[Keys.BEST_STREAK_DAYS] ?: 0 }

    suspend fun setBestStreakDaysIfHigher(candidate: Int): Boolean {
        val current = bestStreakDays.first()
        if (candidate <= current) return false
        context.dataStore.edit { it[Keys.BEST_STREAK_DAYS] = candidate }
        return true
    }

    val firstInstallTimestamp: Flow<Long> =
        context.dataStore.data.map { it[Keys.FIRST_INSTALL_TIMESTAMP] ?: 0L }

    suspend fun setFirstInstallTimestampIfUnset(timestampMillis: Long) {
        context.dataStore.edit {
            if ((it[Keys.FIRST_INSTALL_TIMESTAMP] ?: 0L) <= 0L) {
                it[Keys.FIRST_INSTALL_TIMESTAMP] = timestampMillis
            }
        }
    }

    val isLifetimeUnlocked: Flow<Boolean> =
        context.dataStore.data.map { it[Keys.LIFETIME_UNLOCKED] ?: false }

    suspend fun setLifetimeUnlocked(unlocked: Boolean) {
        context.dataStore.edit { it[Keys.LIFETIME_UNLOCKED] = unlocked }
    }

    companion object {
        const val DEFAULT_COUNTDOWN_SECONDS = 8
        val DEFAULT_MOTIVATION_TEXTS = listOf(
            "Ist das gerade wirklich das, was du tun willst?",
            "Ein Moment Pause, bevor du weiterscrollst."
        )
    }
}
