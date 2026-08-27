package com.stephan.screenlock.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.stephan.screenlock.ui.navigation.Routes
import com.stephan.screenlock.ui.theme.Dimens

// TODO: eigentlicher Home-Screen-Inhalt (Uebersicht, Einstieg zu Settings/Statistics).
@Composable
fun HomeScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize().padding(Dimens.spacingMd)) {
        Text("Digital Wellbeing")
        Button(onClick = { navController.navigate(Routes.SETTINGS) }) {
            Text("Einstellungen")
        }
        Button(onClick = { navController.navigate(Routes.STATISTICS) }) {
            Text("Statistik")
        }
    }
}
