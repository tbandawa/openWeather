package me.tbandawa.android.openweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
class MainViewModel @Inject constructor(
    private val repository: OpenWeatherRepository
) : ViewModel() {

    private val _oneCallWeather = MutableLiveData<NetworkResult<OneCall>>()
    val oneCallWeather: LiveData<NetworkResult<OneCall>> = _oneCallWeather

    fun fetchOneCall(lat: Double, lon: Double){
        viewModelScope.launch {
            repository.fetchOneCall(lat, lon).collect { result ->
                _oneCallWeather.postValue(result)
            }
        }
    }

}