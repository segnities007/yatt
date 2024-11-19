package jp.co.yumemi.droidtraining.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF004e59),
    secondary = Color(0xFF334a50),
    background = Black,
    surface = Black
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF006876),
    secondary = Color(0xFFb1cbd1),
    background = White,
    surface = White
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
