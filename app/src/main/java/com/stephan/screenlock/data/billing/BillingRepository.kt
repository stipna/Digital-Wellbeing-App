package com.stephan.screenlock.data.billing

import android.content.Context
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.PendingPurchasesParams
import com.android.billingclient.api.PurchasesUpdatedListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Kapselt BillingClient-Setup fuer das einmalige Lifetime-INAPP-Produkt.
 * Kein consumePurchase() — Lifetime-Access ist ein dauerhaftes Entitlement.
 * Siehe claude/project-setup.md fuer den vollstaendigen Ablauf
 * (queryProductDetails, launchBillingFlow, restoreExistingPurchases,
 * acknowledgePurchase).
 */
class BillingRepository(context: Context) {

    companion object {
        const val PRODUCT_ID_LIFETIME = "lifetime_access"
    }

    private val purchaseUpdatedListener = PurchasesUpdatedListener { _, _ ->
        // TODO: Purchases gegen PurchaseVerifier pruefen, dann acknowledgePurchase.
    }

    private val billingClient: BillingClient = BillingClient.newBuilder(context)
        .setListener(purchaseUpdatedListener)
        .enablePendingPurchases(PendingPurchasesParams.newBuilder().enableOneTimeProducts().build())
        .build()

    private val _purchaseState = MutableStateFlow<BillingPurchaseState>(BillingPurchaseState.Idle)
    val purchaseState: StateFlow<BillingPurchaseState> = _purchaseState

    fun startConnection() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(result: com.android.billingclient.api.BillingResult) {
                // TODO: queryProductDetails(PRODUCT_ID_LIFETIME) + restoreExistingPurchases()
            }

            override fun onBillingServiceDisconnected() {
                // TODO: Retry-Strategie.
            }
        })
    }

    // TODO: fun launchPurchaseFlow(activity: Activity)
    // TODO: fun restoreExistingPurchases()
}
