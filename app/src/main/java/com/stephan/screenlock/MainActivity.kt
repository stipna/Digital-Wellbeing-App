package com.stephan.screenlock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.stephan.screenlock.ui.PremiumGate
import com.stephan.screenlock.ui.navigation.NavGraph
import com.stephan.screenlock.ui.theme.WellbeingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WellbeingTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PremiumGate {
                        NavGraph()
                    }
                }
            }
        }
    }
}
