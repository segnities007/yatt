package jp.co.yumemi.droidtraining.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import jp.co.yumemi.droidtraining.ui.components.YumemiButton

@Composable
fun Home() {

    val textHeight = 16
    val spacerHeight = 80
    val buttonHeight = 46
    val imageUrl = "https://avatars.githubusercontent.com/u/174174755?v=4"

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(){
            Spacer(modifier = Modifier.height(buttonHeight.dp))
            Spacer(modifier = Modifier.height((textHeight*2).dp))
            Spacer(modifier = Modifier.height(spacerHeight.dp))
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.size(256.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .width(256.dp)
            ){
                DegreeText(textHeight = textHeight, color = "red")
                DegreeText(textHeight = textHeight, color = "blue")
            }
            Spacer(modifier = Modifier.height(spacerHeight.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.width(256.dp)
            ){
                YumemiButton("RELOAD")
                YumemiButton("NEXT")
            }
        }
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
