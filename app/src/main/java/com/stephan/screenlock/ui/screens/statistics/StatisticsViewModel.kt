package com.stephan.screenlock.ui.screens.statistics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// TODO: kombiniert StatisticsRepository mit InstalledAppsRepository zu
// StatisticsUiState, Streak-Rekord-Erkennung gemaess claude/project-setup.md.
class StatisticsViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(StatisticsUiState())
    val uiState: StateFlow<StatisticsUiState> = _uiState.asStateFlow()
}
