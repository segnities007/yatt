package jp.co.yumemi.droidtraining.entry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import jp.co.yumemi.droidtraining.ui.screens.home.Home
import jp.co.yumemi.droidtraining.ui.screens.home.HomeViewModel
import jp.co.yumemi.droidtraining.ui.theme.YumemiWeatherTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val context = applicationContext

        setContent {
            YumemiWeatherTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Home(context = context, viewModel = HomeViewModel(context = context))
                }
            }
        }
    }
}

