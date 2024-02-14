package openweather.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import openweather.domain.models.NetworkResult
import openweather.domain.models.OneCall
import openweather.domain.repository.OpenWeatherRepository

class MainViewModel(
    private val repository: OpenWeatherRepository
) : ViewModel() {

    private val _oneCallWeather = MutableStateFlow<NetworkResult<OneCall>?>(NetworkResult.Empty)
    val oneCallWeather: StateFlow<NetworkResult<OneCall>?> = _oneCallWeather

    private val _showSplash= MutableStateFlow(true)
    val showSplash = _showSplash.asStateFlow()

    fun fetchOneCall(lat: Double, lon: Double){
        if (_oneCallWeather.value is NetworkResult.Success)
            return
        viewModelScope.launch {
            repository.fetchOneCall(lat, lon).collect { result ->
                _oneCallWeather.value = result
                when (result) {
                    is NetworkResult.Success, is NetworkResult.Error, is NetworkResult.Failure -> {
                        _showSplash.value = false
                    } else -> {}
                }
            }
        }
    }

    fun dismissSplash() { _showSplash.value = false }
}