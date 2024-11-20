package jp.co.yumemi.droidtraining.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White


object DarkColors {
    val primary = Color(0xFF004e59)
    val secondary = Color(0xFF334a50)
    val background = Black
    val surface = Black
}

object LightColor {
    val primary = Color(0xFF006876)
    val secondary = Color(0xFFb1cbd1)
    val background = White
    val surface = White
}


private val DarkColorScheme = darkColorScheme(
    primary = DarkColors.primary,
    secondary = DarkColors.secondary,
    background = DarkColors.background,
    surface = DarkColors.surface
)

private val LightColorScheme = lightColorScheme(
    primary = LightColor.primary,
    secondary = LightColor.secondary,
    background = LightColor.background,
    surface = LightColor.surface
)

@Composable
fun YumemiWeatherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        shapes = Shapes(),
        content = content
    )
}
