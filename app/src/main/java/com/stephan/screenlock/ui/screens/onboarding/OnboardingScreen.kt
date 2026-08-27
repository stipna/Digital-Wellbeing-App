package com.stephan.screenlock.ui.screens.onboarding

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

// TODO: 3-Screen-Flow (Problem-Framing / Funktionsprinzip / App-Auswahl),
// animierte Uebergaenge + Indikator-Dots gemaess claude/project-setup.md.
@Composable
fun OnboardingScreen(
    navController: NavHostController,
    viewModel: OnboardingViewModel = viewModel()
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
        when (page) {
            0 -> Text("Problem-Framing")
            1 -> Text("Funktionsprinzip")
            else -> Text("App-Auswahl")
        }
    }
}
