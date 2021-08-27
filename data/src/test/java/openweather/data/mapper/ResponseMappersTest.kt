package openweather.data.mapper

import openweather.data.base.ApiBaseTest
import openweather.data.remote.response.CurrentWeatherResponse
import openweather.data.remote.response.FiveDayWeatherForecastResponse
import openweather.data.remote.response.OneCallResponse
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ResponseMappersTest: ApiBaseTest() {

    @Test
    fun `test current weather mapper`() {

        val currentWeatherResponse = readJsonResponse<CurrentWeatherResponse>("current_weather.json")
        val currentWeather = currentWeatherMapper.mapToModel(currentWeatherResponse)

        assertThat(currentWeatherResponse.base, `is`(currentWeather.base))

    }

    @Test
    fun `test five day weather mapper`() {

        val fiveDayWeatherForecastResponse = readJsonResponse<FiveDayWeatherForecastResponse>("five_day_weather.json")
        val fiveDayWeather = fiveDayWeatherMapper.mapToModel(fiveDayWeatherForecastResponse)

        assertThat(fiveDayWeatherForecastResponse.cod, `is`(fiveDayWeather.cod))

    }

    @Test
    fun `test one call weather mapper`() {

        val oneCallResponse = readJsonResponse<OneCallResponse>("one_call_weather.json")
        val oneCall = oneCallMapper.mapToModel(oneCallResponse)

        assertThat(oneCallResponse.timezone, `is`(oneCall.timezone))

    }

}