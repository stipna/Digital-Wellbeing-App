package com.stephan.screenlock.ui.screens.lock

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stephan.screenlock.ui.theme.Dimens

/**
 * Kein reiner Blocker, sondern ein Reflexionsmoment. Progress-Ring +
 * Motivationstext, zwei Aktionen erst nach Ablauf des Countdowns.
 * TODO: Animatable-Countdown, Fade-in/out, SettingsRepository-Anbindung
 * (Countdown-Dauer + Motivations-Texte) gemaess claude/project-setup.md.
 */
@Composable
fun LockScreen(blockedPackageName: String, onFinish: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.spacingLg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.padding(bottom = Dimens.spacingLg))
        Text(
            text = "Ist das gerade wirklich das, was du tun willst?",
            style = MaterialTheme.typography.titleLarge
        )
        Button(
            onClick = onFinish,
            modifier = Modifier.padding(top = Dimens.spacingXl)
        ) {
            Text("Zurueck zum Home-Bildschirm")
        }
    }
}
