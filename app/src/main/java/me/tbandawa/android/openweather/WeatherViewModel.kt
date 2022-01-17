package me.tbandawa.android.openweather

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import openweather.domain.models.NetworkResult
import openweather.domain.models.OneCall
import openweather.domain.repository.OpenWeatherRepository
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: OpenWeatherRepository
) : ViewModel() {

    val oneCallWeather: MutableState<NetworkResult<OneCall>?> = mutableStateOf(null)

    fun fetchOneCall(lat: Long, lon: Long){
        viewModelScope.launch {
            repository.fetchOneCall(lat, lon).collect { result ->
                oneCallWeather.value = result
            }
        }
    }

}