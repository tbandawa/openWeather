package openweather.domain.repository

import kotlinx.coroutines.flow.Flow
import openweather.domain.models.CurrentWeather
import openweather.domain.models.FiveDayWeatherForecast
import openweather.domain.models.NetworkResult
import openweather.domain.models.OneCall

interface OpenWeatherRepository {

    suspend fun fetchCurrentWeather(city: String): Flow<NetworkResult<CurrentWeather>>

    suspend fun fetchOneCall(lat: Long, lon: Long): Flow<NetworkResult<OneCall>>

    suspend fun fetchFiveDayWeather(city: String): Flow<NetworkResult<FiveDayWeatherForecast>>

}