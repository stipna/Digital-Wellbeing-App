package com.stephan.screenlock.ui.screens.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

// TODO: pro App-Card mit Switch + Slider (5-180 Min, 5er-Schritte) gemaess
// claude/project-setup.md; hier nur Liste der installierten Apps als Basis.
@Composable
fun SettingsScreen(viewModel: SettingsViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(uiState.installedApps) { app ->
            Text(app.label)
        }
    }
}
