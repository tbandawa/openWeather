package openweather.data.remote.api

import openweather.data.remote.response.CurrentWeatherResponse
import openweather.data.remote.response.FiveDayWeatherForecastResponse
import openweather.data.remote.response.OneCallResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("weather")
    suspend fun fetchCurrentWeather(@Query("appid") appid: String, @Query("q") q: String) : Response<CurrentWeatherResponse>

    @GET("onecall")
    suspend fun fetchOneCall(@Query("appid") appid: String, @Query("lat") lat: Double, @Query("lon") lon: Double) : Response<OneCallResponse>

    @GET("forecast")
    suspend fun fetchFiveDayWeather(@Query("appid") appid: String, @Query("q") q: String) : Response<FiveDayWeatherForecastResponse>

}