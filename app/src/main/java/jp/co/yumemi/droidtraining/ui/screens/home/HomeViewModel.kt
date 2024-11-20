package jp.co.yumemi.droidtraining.ui.screens.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import jp.co.yumemi.api.YumemiWeather
import kotlinx.coroutines.launch

class HomeViewModel(context: Context): ViewModel() {

    val weatherText = MutableStateFlow("")
    val isShowDialog = MutableStateFlow(false)
//    val upperDegree = MutableStateFlow(0)
//    val lowerDegree = MutableStateFlow(0)
    val yumemiWeather = YumemiWeather(context = context)

    init {
        weatherText.value = yumemiWeather.fetchSimpleWeather()
    }

    fun showDialog(){
        isShowDialog.value = true
    }

    fun hideDialog(){
        isShowDialog.value = false
    }

    fun changeWeatherText(){
        viewModelScope.launch{
            try {
                weatherText.value = yumemiWeather.fetchThrowsWeather()
            }catch (e: Throwable){
                println(e)
                showDialog()
            }
        }
    }
}
