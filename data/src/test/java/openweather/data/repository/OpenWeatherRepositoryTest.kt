package openweather.data.repository

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import openweather.data.base.BaseMockTest
import openweather.data.remote.response.*
import openweather.domain.models.CurrentWeather
import openweather.domain.models.FiveDayWeatherForecast
import openweather.domain.models.NetworkResult
import openweather.domain.models.OneCall
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.mockito.ArgumentMatchers.*
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

}