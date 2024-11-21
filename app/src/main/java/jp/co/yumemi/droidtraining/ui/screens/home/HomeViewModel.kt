package jp.co.yumemi.droidtraining.ui.screens.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import jp.co.yumemi.api.YumemiWeather
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(context: Context): ViewModel() {

    private val _weatherText = MutableStateFlow("")
    val weatherText: StateFlow<String> = _weatherText.asStateFlow()
    private val _isShowDialog = MutableStateFlow(false)
    val isShowDialog = _isShowDialog.asStateFlow()
//    private val _upperDegree = MutableStateFlow(0)
//    private val _lowerDegree = MutableStateFlow(0)
    private val yumemiWeather = YumemiWeather(context = context)

    init {
        _weatherText.value = yumemiWeather.fetchSimpleWeather()
    }

    fun showDialog(){
        _isShowDialog.value = true
    }

    fun hideDialog(){
        _isShowDialog.value = false
    }

    fun changeWeatherText(){
        viewModelScope.launch{
            try {
                _weatherText.value = yumemiWeather.fetchThrowsWeather()
            }catch (e: Throwable){
                println(e)
                showDialog()
            }
        }
    }
}
