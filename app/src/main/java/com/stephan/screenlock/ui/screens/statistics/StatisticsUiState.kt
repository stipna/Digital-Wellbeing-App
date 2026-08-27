package com.stephan.screenlock.ui.screens.statistics

import com.stephan.screenlock.data.repository.AppWeeklyUsage

data class StatisticsUiState(
    val weeklyUsage: List<AppWeeklyUsage> = emptyList(),
    val hasActiveLimits: Boolean = false,
    val currentStreakDays: Int = 0
)
