package openweather.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import openweather.domain.models.NetworkResult
import openweather.domain.models.OneCall
import openweather.domain.repository.OpenWeatherRepository

class MainViewModel(
    private val repository: OpenWeatherRepository
) : ViewModel() {

    private val _oneCallWeather = MutableStateFlow<NetworkResult<OneCall>?>(NetworkResult.Empty)
    val oneCallWeather: StateFlow<NetworkResult<OneCall>?> = _oneCallWeather

    fun fetchOneCall(lat: Double, lon: Double){
        viewModelScope.launch {
            repository.fetchOneCall(lat, lon).collect { result ->
                _oneCallWeather.value = result
            }
        }
    }
}