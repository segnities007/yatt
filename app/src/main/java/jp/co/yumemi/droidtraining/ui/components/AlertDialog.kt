package jp.co.yumemi.droidtraining.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun YumemiAlert(
    isShowDialog: Boolean,
    reloadWeather: () -> Unit = {},
    hideDialog: () -> Unit = {},
){

    if(isShowDialog){
        AlertDialog(
            title = {Text("Error")},
            text = {Text(text = "エラーが発生しました。")},
            onDismissRequest = {},
            confirmButton = {
                TextButton(
                    onClick = {
                        reloadWeather()
                        hideDialog()
                    }) {Text("RELOAD")}
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        hideDialog()
                    }) {Text("CLOSE")}
            }
        )
    }
}
