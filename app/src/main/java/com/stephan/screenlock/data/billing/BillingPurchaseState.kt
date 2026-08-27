package com.stephan.screenlock.data.billing

sealed interface BillingPurchaseState {
    data object Idle : BillingPurchaseState
    data object Pending : BillingPurchaseState
    data object Purchased : BillingPurchaseState
    data class Error(val message: String) : BillingPurchaseState
}
