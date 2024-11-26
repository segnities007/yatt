package jp.co.yumemi.droidtraining.ui.screens.home

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
import jp.co.yumemi.droidtraining.ui.components.YumemiButton
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun Home(
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val textHeight = 16
    val spacerHeight = 80
    val buttonHeight = 46
    val weatherText by  viewModel.weatherText.collectAsState()
    val isShowDialog by viewModel.isShowDialog.collectAsState()

    if(isShowDialog){
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
            WeatherImage(textHeight = textHeight, weatherText = weatherText, vectorColor = viewModel::vectorColor, vectorID = viewModel::vectorID)
            Spacer(modifier = Modifier.height(spacerHeight.dp))
            ChangeButtons(reloadHandler = viewModel::changeWeatherText, nextHandler = {/*TODO*/})
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
    vectorID: (String) -> Int,
    vectorColor: (String) -> ColorFilter,
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
