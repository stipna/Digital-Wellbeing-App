package com.stephan.screenlock.ui.screens.lock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.stephan.screenlock.ui.theme.WellbeingTheme

class BlockedAppActivity : ComponentActivity() {

    companion object {
        const val EXTRA_PACKAGE_NAME = "extra_package_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val packageName = intent.getStringExtra(EXTRA_PACKAGE_NAME).orEmpty()
        setContent {
            WellbeingTheme {
                LockScreen(blockedPackageName = packageName, onFinish = { finish() })
            }
        }
    }
}
