package com.stephan.screenlock.ui.screens.onboarding

import com.stephan.screenlock.domain.model.InstalledApp

data class OnboardingUiState(
    val currentPage: Int = 0,
    val installedApps: List<InstalledApp> = emptyList(),
    val selectedPackages: Set<String> = emptySet()
)
