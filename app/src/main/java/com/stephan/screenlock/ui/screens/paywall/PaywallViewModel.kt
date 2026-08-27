package com.stephan.screenlock.ui.screens.paywall

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Schreibt isLifetimeUnlocked = true erst NACH Verifikation + Acknowledgement,
 * nie direkt aus der UI. Siehe claude/project-setup.md.
 */
class PaywallViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(PaywallUiState())
    val uiState: StateFlow<PaywallUiState> = _uiState.asStateFlow()

    // TODO: BillingRepository-Anbindung (launchPurchaseFlow, restoreExistingPurchases).
}
