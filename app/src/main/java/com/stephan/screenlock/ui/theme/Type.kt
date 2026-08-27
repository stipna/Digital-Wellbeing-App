package com.stephan.screenlock.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Ein Font-Weight-Paar (Normal/Medium), kein Bold in Fliesstext.
// Systemfont als Default, zentral austauschbar.
private val WellbeingFontFamily = FontFamily.Default

val WellbeingTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = WellbeingFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = WellbeingFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 30.sp
    ),
    labelLarge = TextStyle(
        fontFamily = WellbeingFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    )
)
