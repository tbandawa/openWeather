package openweather.domain.repository

import kotlinx.coroutines.flow.Flow
import openweather.domain.models.*

interface OpenWeatherRepository {

    suspend fun fetchCurrentWeather(city: String): Flow<NetworkResult<CurrentWeather>>

    suspend fun fetchOneCall(lat: Double, lon: Double): Flow<NetworkResult<OneCall>>

    suspend fun fetchFiveDayWeather(city: String): Flow<NetworkResult<FiveDayWeatherForecast>>

}