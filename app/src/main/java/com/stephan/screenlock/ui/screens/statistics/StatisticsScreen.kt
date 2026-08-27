package com.stephan.screenlock.ui.screens.statistics

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

// TODO: Streak-Badge + Card pro App (WeeklyBarChart, DayDetailPopup,
// WeekTrendArrow) gemaess claude/project-setup.md.
@Composable
fun StatisticsScreen(viewModel: StatisticsViewModel = viewModel()) {
    Text("Statistik", modifier = Modifier.fillMaxSize())
}
