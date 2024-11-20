package jp.co.yumemi.droidtraining.ui.screens.home

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import jp.co.yumemi.droidtraining.R
import jp.co.yumemi.droidtraining.ui.components.YumemiButton
import jp.co.yumemi.droidtraining.ui.theme.WeatherColor

@Composable
fun Home(
    viewModel: HomeViewModel,
) {

    val textHeight = 16
    val spacerHeight = 80
    val buttonHeight = 46
    val weatherText = viewModel.weatherText.collectAsState()
    val isShowDialog = viewModel.isShowDialog.collectAsState()

    if(isShowDialog.value){
        AlertDialog(
            title = {Text("Error")},
            text = {Text(text = "エラーが発生しました。")},
            onDismissRequest = {},
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.changeWeatherText()
                        viewModel.hideDialog()
                    }) {Text("RELOAD")}
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        viewModel.hideDialog()
                    }) {Text("CLOSE")}
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(){
            UpperSpacers(buttonHeight = buttonHeight, spacerHeight = spacerHeight, textHeight = textHeight)
            WeatherImage(textHeight = textHeight, weatherText = weatherText.value)
            Spacer(modifier = Modifier.height(spacerHeight.dp))
            ChangeButtons(reloadHandler = {viewModel.changeWeatherText()}, nextHandler = {/*TODO*/})
        }
    }
}

@Composable
private fun UpperSpacers(
    buttonHeight: Int,
    textHeight: Int,
    spacerHeight: Int,
){
    Spacer(modifier = Modifier.height(buttonHeight.dp))
    Spacer(modifier = Modifier.height((textHeight*2).dp))
    Spacer(modifier = Modifier.height(spacerHeight.dp))
}

@Composable
private fun WeatherImage(
    textHeight: Int,
    weatherText: String,
){
    Image(
        painter = painterResource(id = vectorID(weatherText)),
        contentDescription = null,
        colorFilter = vectorColor(weatherText),
        modifier = Modifier.size(256.dp),
    )
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .width(256.dp)
    ){
        DegreeText(textHeight = textHeight, color = "red")
        DegreeText(textHeight = textHeight, color = "blue")
    }
}

private fun vectorID(
    weatherText: String
): Int{
    when(weatherText){
        "sunny"     -> return R.drawable.sunny
        "rainy"     -> return R.drawable.rainy
        "cloudy"    -> return R.drawable.cloudy
        "snow"      -> return R.drawable.snow
    }
    return R.drawable.sunny
}

private fun vectorColor(
    weatherText: String
): ColorFilter{
    when(weatherText){
        "sunny"     -> return ColorFilter.tint(WeatherColor.sunny)
        "rainy"     -> return ColorFilter.tint(WeatherColor.rainy)
        "cloudy"    -> return ColorFilter.tint(WeatherColor.cloudy)
        "snow"      -> return ColorFilter.tint(WeatherColor.snow)
    }
    return ColorFilter.tint(WeatherColor.sunny)
}

@Composable
private fun DegreeText(
    textHeight: Int,
    color: String
){
    when(color){
        "red"   -> Text("+", modifier = Modifier.height(textHeight.dp), color = Color.Red)
        "blue"  -> Text("-", modifier = Modifier.height(textHeight.dp), color = Color.Blue)
        else    -> Text("error", modifier = Modifier.height(textHeight.dp))
    }
}

@Composable
private fun ChangeButtons(
    reloadHandler: () -> Unit,
    nextHandler: () -> Unit,
){
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.width(256.dp)
    ){
        YumemiButton("RELOAD", handler = reloadHandler)
        YumemiButton("NEXT", handler = nextHandler)
    }
}
