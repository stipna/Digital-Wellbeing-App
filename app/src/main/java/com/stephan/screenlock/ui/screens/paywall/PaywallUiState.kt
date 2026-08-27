package com.stephan.screenlock.ui.screens.paywall

data class PaywallUiState(
    val formattedPrice: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
