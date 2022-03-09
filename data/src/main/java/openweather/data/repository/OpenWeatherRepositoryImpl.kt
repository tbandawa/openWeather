package openweather.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import openweather.data.BuildConfig
import openweather.data.mapper.CurrentWeatherMapper
import openweather.data.mapper.ErrorResponseMapper
import openweather.data.mapper.FiveDayWeatherMapper
import openweather.data.mapper.OneCallMapper
import openweather.data.remote.api.OpenWeatherApi
import openweather.data.remote.response.*
import openweather.domain.mapper.ResponseMapper
import openweather.domain.models.*
import openweather.domain.repository.OpenWeatherRepository
import javax.inject.Inject

class OpenWeatherRepositoryImpl @Inject constructor(
    private val openWeatherApi: OpenWeatherApi
) : OpenWeatherRepository, BaseResponse() {

    private val currentWeatherMapper: ResponseMapper<CurrentWeatherResponse, CurrentWeather> by lazy { CurrentWeatherMapper() }

    private val oneCallMapper: ResponseMapper<OneCallResponse, OneCall> by lazy { OneCallMapper() }

    private val fiveDayWeatherMapper: ResponseMapper<FiveDayWeatherForecastResponse, FiveDayWeatherForecast> by lazy { FiveDayWeatherMapper() }

    private val errorMapper: ResponseMapper<ErrorResponse, Error> by lazy { ErrorResponseMapper() }

    override suspend fun fetchCurrentWeather(city: String): Flow<NetworkResult<CurrentWeather>> = flow {
        emit(NetworkResult.Loading())
        emit(safeApiCall(currentWeatherMapper) {
            openWeatherApi.fetchCurrentWeather(
                BuildConfig.OPEN_WEATHER_API_KEY,
                city
            )
        })
    }.flowOn(Dispatchers.IO)

    override suspend fun fetchOneCall(lat: Double, lon: Double): Flow<NetworkResult<OneCall>> = flow {
        emit(NetworkResult.Loading())
        emit(safeApiCall(oneCallMapper) {
            openWeatherApi.fetchOneCall(
                BuildConfig.OPEN_WEATHER_API_KEY,
                lat,
                lon
            )
        })
    }.flowOn(Dispatchers.IO)

    override suspend fun fetchFiveDayWeather(city: String): Flow<NetworkResult<FiveDayWeatherForecast>> = flow {
        emit(NetworkResult.Loading())
        emit(safeApiCall(fiveDayWeatherMapper) {
            openWeatherApi.fetchFiveDayWeather(
                BuildConfig.OPEN_WEATHER_API_KEY,
                city
            )
        })
    }.flowOn(Dispatchers.IO)

}