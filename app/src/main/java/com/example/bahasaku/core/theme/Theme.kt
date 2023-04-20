package com.example.bahasaku.core.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

//private val DarkColorPalette = darkColors(
//    primary = Purple200,
//    primaryVariant = Purple700,
//    secondary = Teal200
//)

private val LightColorPalette = lightColors(
    primary = Color(0XFFAB47BC),
    primaryVariant = Color(0XFFCE93D8),
    secondary = Color(0XFF42A5F5),
    onPrimary = White,
    background = Color(0XFFF9F9F9)

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
fun BahasakuTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = LightColorPalette
//        if (darkTheme) {
//        DarkColorPalette
//    } else {
//        LightColorPalette
//    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes
    ) {
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null,
            content = content
        )
    }
}