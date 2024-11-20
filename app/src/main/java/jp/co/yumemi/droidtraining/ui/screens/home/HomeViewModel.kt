package jp.co.yumemi.droidtraining.ui.screens.home

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import jp.co.yumemi.api.YumemiWeather

class HomeViewModel(context: Context): ViewModel() {

    val weatherText = MutableStateFlow("sunny")
    val upperDegree = MutableStateFlow(0)
    val lowerDegree = MutableStateFlow(0)

    init {
        weatherText.value = YumemiWeather(
            context = context,
        ).fetchSimpleWeather()
    }

    fun changeWeatherText(context: Context){
        weatherText.value = YumemiWeather(
            context = context,
        ).fetchSimpleWeather()
    }
}

class WeatherImageViewModel : ViewModel() {

}

class DegreeViewModel : ViewModel() {

}
