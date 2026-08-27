package com.stephan.screenlock.ui.screens.paywall

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stephan.screenlock.ui.theme.Dimens

// TODO: Hard-Paywall ohne Zurueck-Navigation, dynamisches Preis-Label,
// "Kauf nicht erkannt?"-Link gemaess claude/project-setup.md.
@Composable
fun PaywallScreen(viewModel: PaywallViewModel = viewModel()) {
    Column(modifier = Modifier.fillMaxSize().padding(Dimens.spacingLg)) {
        Text("Lifetime Access")
        Button(onClick = { /* TODO: launchPurchaseFlow */ }) {
            Text("Freischalten")
        }
    }
}
