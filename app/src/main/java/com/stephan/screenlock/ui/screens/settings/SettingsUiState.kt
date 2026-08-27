package com.stephan.screenlock.ui.screens.settings

import com.stephan.screenlock.data.local.entity.AppLimit
import com.stephan.screenlock.domain.model.InstalledApp

data class SettingsUiState(
    val installedApps: List<InstalledApp> = emptyList(),
    val limits: List<AppLimit> = emptyList()
)
