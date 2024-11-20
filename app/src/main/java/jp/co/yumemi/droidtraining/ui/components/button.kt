package jp.co.yumemi.droidtraining.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import jp.co.yumemi.api.YumemiWeather
import jp.co.yumemi.droidtraining.ui.theme.YumemiWeatherTheme

@Composable
fun YumemiButton(
    text: String,
    handler: () -> Unit
){
    ElevatedButton(
        onClick = handler,
        shape = RectangleShape,
        modifier = Modifier
            .height(46.dp)
            .width(100.dp)
    ) {
        Text(text)
    }
}
