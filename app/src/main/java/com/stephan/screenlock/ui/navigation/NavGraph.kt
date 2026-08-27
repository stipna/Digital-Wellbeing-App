package com.stephan.screenlock.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stephan.screenlock.ui.screens.home.HomeScreen
import com.stephan.screenlock.ui.screens.onboarding.OnboardingScreen
import com.stephan.screenlock.ui.screens.settings.SettingsScreen
import com.stephan.screenlock.ui.screens.statistics.StatisticsScreen

object Routes {
    const val ROOT = "root"
    const val ONBOARDING = "onboarding"
    const val HOME = "home"
    const val SETTINGS = "settings"
    const val STATISTICS = "statistics"
}

// TODO: NavHost-Aufbau gemaess claude/project-setup.md.
// ROOT liest SettingsRepository.onboardingCompleted einmalig und navigiert
// ersetzend (popUpTo(ROOT){inclusive=true}) zu ONBOARDING oder HOME.
// Startdestination hier vorlaeufig HOME, bis ROOT-Gate eingebaut ist.
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.ONBOARDING) { OnboardingScreen(navController) }
        composable(Routes.HOME) { HomeScreen(navController) }
        composable(Routes.SETTINGS) { SettingsScreen() }
        composable(Routes.STATISTICS) { StatisticsScreen() }
    }
}
