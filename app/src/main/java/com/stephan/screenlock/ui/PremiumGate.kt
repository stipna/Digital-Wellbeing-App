package com.stephan.screenlock.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.stephan.screenlock.ui.screens.paywall.PaywallScreen

/**
 * Reaktiver Wrapper um den NavHost. Setzt firstInstallTimestamp beim ersten
 * Rendern und ersetzt content() durch PaywallScreen(), sobald der Trial
 * abgelaufen ist und kein Lifetime-Kauf vorliegt.
 *
 * TODO: SettingsRepository-Anbindung (firstInstallTimestamp/isLifetimeUnlocked)
 * gemaess claude/project-setup.md einbauen — aktuell Platzhalter-Logik, damit
 * das Modul kompiliert.
 */
@Composable
fun PremiumGate(content: @Composable () -> Unit) {
    LaunchedEffect(Unit) {
        // TODO: settingsRepository.setFirstInstallTimestampIfUnset(System.currentTimeMillis())
    }

    val trialExpired = false // TODO: aus TrialStatus.isTrialExpired(...) ableiten
    val lifetimeUnlocked = false // TODO: aus SettingsRepository.isLifetimeUnlocked ableiten

    if (trialExpired && !lifetimeUnlocked) {
        PaywallScreen()
    } else {
        content()
    }
}
