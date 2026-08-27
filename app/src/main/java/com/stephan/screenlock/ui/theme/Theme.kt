package com.stephan.screenlock.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Dynamic Color (Material You) bewusst nicht verwendet, siehe claude/design-system.md.

private val LightColors = lightColorScheme(
    primary = PetrolBlueLight,
    secondary = SageGreenLight,
    tertiary = SandTertiary,
    error = TerracottaError,
    background = OffWhiteBackground,
    surface = OffWhiteBackground
)

private val DarkColors = darkColorScheme(
    primary = PetrolBlueDark,
    secondary = SageGreenDark,
    tertiary = SandTertiary,
    error = TerracottaError,
    background = AnthraciteBackground,
    surface = AnthraciteBackground
)

@Composable
fun WellbeingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colorScheme,
        typography = WellbeingTypography,
        shapes = WellbeingShapes,
        content = content
    )
}
