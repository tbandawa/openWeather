package openweather.data.mapper

import openweather.data.ApiBaseTest
import openweather.data.remote.response.CurrentWeatherResponse
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

}