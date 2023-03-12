package com.hariomahlawat.generatorcare.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Secondary200,
    primaryVariant = Secondary500,
    secondary = Secondary200,
    secondaryVariant = Secondary200,
    onPrimary = Color.Black,
    onSecondary = Color.Black

)

private val LightColorPalette = lightColors(
    primary = Primary200,
    primaryVariant = Primary500,
    secondary = Secondary200,
    secondaryVariant = Secondary200,
    onPrimary = Color.Black,
    onSecondary = Color.Black

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun GeneratorCareTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}