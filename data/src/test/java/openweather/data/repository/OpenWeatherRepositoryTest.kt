package openweather.data.repository

import com.google.gson.Gson
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import openweather.data.base.BaseMockTest
import openweather.data.remote.response.CurrentWeatherResponse
import openweather.data.remote.response.ErrorResponse
import openweather.data.remote.response.FiveDayWeatherForecastResponse
import openweather.data.remote.response.OneCallResponse
import openweather.domain.models.CurrentWeather
import openweather.domain.models.FiveDayWeatherForecast
import openweather.domain.models.NetworkResult
import openweather.domain.models.OneCall
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import retrofit2.Response

open class OpenWeatherRepositoryTest : BaseMockTest() {

    @Test
    fun `test fetch current weather`() = runBlocking {

        val currentWeatherResponse = readJsonResponse<CurrentWeatherResponse>("current_weather.json")

        `when`(openWeatherApi.fetchCurrentWeather(anyString(), anyString())).thenReturn(Response.success(currentWeatherResponse))

        openWeatherRepository.fetchCurrentWeather("city").collect { value: NetworkResult<CurrentWeather> ->

            if(value is NetworkResult.Success) {
                MatcherAssert.assertThat(
                    value.data?.base,
                    CoreMatchers.`is`("stations")
                )
            }

        }

    }

    @Test
    fun `test fetch five day weather`() = runBlocking {

        val fiveDayWeatherForecastResponse = readJsonResponse<FiveDayWeatherForecastResponse>("five_day_weather.json")

        `when`(openWeatherApi.fetchFiveDayWeather(anyString(), anyString())).thenReturn(Response.success(fiveDayWeatherForecastResponse))

        openWeatherRepository.fetchFiveDayWeather("city").collect { value: NetworkResult<FiveDayWeatherForecast> ->

            if(value is NetworkResult.Success) {
                MatcherAssert.assertThat(
                    value.data?.city?.name,
                    CoreMatchers.`is`("Beijing")
                )
            }

        }

    }

    @Test
    fun `test one call weather`() = runBlocking {

        val oneCallResponse = readJsonResponse<OneCallResponse>("one_call_weather.json")

        `when`(openWeatherApi.fetchOneCall(anyString(), anyDouble(), anyDouble())).thenReturn(Response.success(oneCallResponse))

        openWeatherRepository.fetchOneCall(1.0, 1.0).collect { value: NetworkResult<OneCall> ->

            if(value is NetworkResult.Success) {
                MatcherAssert.assertThat(
                    value.data?.current?.sunrise,
                    CoreMatchers.`is`(1630815384)
                )
            }

        }

    }

    @Test
    fun `test error 400`() = runBlocking {

        val errorResponse = readJsonResponse<ErrorResponse>("error.json")

        var gson = Gson()
        var jsonString = gson.toJson(errorResponse)

        val responseBody: ResponseBody = jsonString
            .toResponseBody("application/json".toMediaTypeOrNull())

        `when`(openWeatherApi.fetchOneCall(anyString(), anyDouble(), anyDouble()))
            .thenReturn(Response.error(404, responseBody))

        openWeatherRepository.fetchOneCall(1.0, 1.0).collect { value: NetworkResult<OneCall> ->

            if(value is NetworkResult.Error) {
                MatcherAssert.assertThat(value.data?.cod, CoreMatchers.`is`("400"))
                MatcherAssert.assertThat(value.data?.message, CoreMatchers.`is`("wrong latitude"))
            }

        }

    }

}