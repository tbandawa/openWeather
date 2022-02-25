package me.tbandawa.android.openweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import openweather.domain.models.NetworkResult
import openweather.domain.models.OneCall
import openweather.domain.repository.OpenWeatherRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: OpenWeatherRepository
) : ViewModel() {

    private val _oneCallWeather = MutableStateFlow<NetworkResult<OneCall>?>(NetworkResult.Loading())
    val oneCallWeather: StateFlow<NetworkResult<OneCall>?> = _oneCallWeather

    fun fetchOneCall(lat: Double, lon: Double){
        viewModelScope.launch {
            repository.fetchOneCall(lat, lon).collect { result ->
                _oneCallWeather.value = result
            }
        }
    }

}