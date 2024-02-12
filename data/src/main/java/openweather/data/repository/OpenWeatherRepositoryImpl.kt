package openweather.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import openweather.data.mapper.CurrentWeatherMapper
import openweather.data.mapper.ErrorMapper
import openweather.data.mapper.FiveDayWeatherMapper
import openweather.data.mapper.OneCallMapper
import openweather.data.remote.api.OpenWeatherApi
import openweather.data.remote.response.BaseResponse
import openweather.domain.models.CurrentWeather
import openweather.domain.models.FiveDayWeatherForecast
import openweather.domain.models.NetworkResult
import openweather.domain.models.OneCall
import openweather.domain.repository.OpenWeatherRepository

class OpenWeatherRepositoryImpl (
    private val openWeatherApi: OpenWeatherApi,
    private val currentWeatherMapper: CurrentWeatherMapper,
    private val oneCallMapper: OneCallMapper,
    private val fiveDayWeatherMapper: FiveDayWeatherMapper,
    private val errorMapper: ErrorMapper
) : OpenWeatherRepository, BaseResponse(errorMapper) {

    override suspend fun fetchCurrentWeather(city: String): Flow<NetworkResult<CurrentWeather>> = flow {
        emit(NetworkResult.Loading)
        emit(safeApiCall {
            currentWeatherMapper.mapToModel(openWeatherApi.fetchCurrentWeather(city))
        })
    }.flowOn(Dispatchers.IO)

    override suspend fun fetchOneCall(lat: Double, lon: Double): Flow<NetworkResult<OneCall>> = flow {
        emit(NetworkResult.Loading)
        emit(safeApiCall {
            oneCallMapper.mapToModel(openWeatherApi.fetchOneCall(lat, lon))
        })
    }.flowOn(Dispatchers.IO)

    override suspend fun fetchFiveDayWeather(city: String): Flow<NetworkResult<FiveDayWeatherForecast>> = flow {
        emit(NetworkResult.Loading)
        emit(safeApiCall {
            fiveDayWeatherMapper.mapToModel(openWeatherApi.fetchFiveDayWeather(city))
        })
    }.flowOn(Dispatchers.IO)
}