package me.tbandawa.android.openweather

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import openweather.domain.models.CurrentWeather
import openweather.domain.models.NetworkResult
import openweather.domain.models.OneCall
import openweather.domain.repository.OpenWeatherRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: OpenWeatherRepository
) : ViewModel() {

    val oneCallWeather: MutableState<NetworkResult<OneCall>?> = mutableStateOf(null)

    val currentWeather: MutableState<NetworkResult<CurrentWeather>?> = mutableStateOf(null)

    init {
        fetchWeather()
    }

    private fun fetchWeather(){
        viewModelScope.launch {
            repository.fetchOneCall((-26.2023).toLong(), 28.0436.toLong()).collect { result ->

                when(result) {
                    is NetworkResult.Loading -> {
                        Timber.d("loading...")
                    }
                    is NetworkResult.Success -> {
                        Timber.d(result.data.toString())
                    }
                    is NetworkResult.Error -> {
                        Timber.d(result.message)
                    }
                }

                oneCallWeather.value = result
            }
        }
    }

    private fun fetchCurrentWeather(){
        viewModelScope.launch {
            repository.fetchCurrentWeather("Johannesburg").collect { result ->

                when(result) {
                    is NetworkResult.Loading -> {
                        Timber.d("loading...")
                    }
                    is NetworkResult.Success -> {
                        Timber.d(result.data.toString())
                    }
                    is NetworkResult.Error -> {
                        Timber.d(result.message)
                    }
                }

                currentWeather.value = result
            }
        }
    }

}