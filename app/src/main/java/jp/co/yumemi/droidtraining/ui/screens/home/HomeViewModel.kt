package jp.co.yumemi.droidtraining.ui.screens.home

import androidx.compose.ui.graphics.ColorFilter
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.yumemi.api.YumemiWeather
import jp.co.yumemi.droidtraining.R
import jp.co.yumemi.droidtraining.ui.theme.WeatherColor
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val yumemiWeather: YumemiWeather,
) : ViewModel() {

    val weather = "weatherText"
    val showDialog = "isShowDialog"
    val showProgressBar = "isShowProgressBar"
    val weatherText: StateFlow<String> = savedStateHandle.getStateFlow(weather, "sunny")
    val isShowDialog: StateFlow<Boolean> = savedStateHandle.getStateFlow(showDialog, false)
    val isShowProgressBar: StateFlow<Boolean> = savedStateHandle.getStateFlow(showProgressBar, false)


    fun showDialog() {
        savedStateHandle[showDialog] = true
    }

    fun hideDialog() {
        savedStateHandle[showDialog] = false
    }

    fun showProgressBar(){
        savedStateHandle[showProgressBar] = true
    }

    fun hideProgressbar(){
        savedStateHandle[showProgressBar] = false
    }

    fun nextWeather(){
        viewModelScope.launch {

        }
    }

    fun reloadWeather() {
        viewModelScope.launch {
            try {
                showProgressBar()
                val newWeather = yumemiWeather.fetchWeatherAsync()
                savedStateHandle["weatherText"] = newWeather
            } catch (e: Throwable) {
                println(e)
                showDialog()
            }finally {
                hideProgressbar()
            }
        }
    }

    fun vectorColor(
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

    fun vectorID(
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
}

