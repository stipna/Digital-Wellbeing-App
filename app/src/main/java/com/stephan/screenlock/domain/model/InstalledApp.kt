package com.stephan.screenlock.domain.model

import android.graphics.Bitmap

data class InstalledApp(
    val packageName: String,
    val label: String,
    val icon: Bitmap?
)
