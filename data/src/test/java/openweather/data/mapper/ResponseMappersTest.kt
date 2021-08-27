package openweather.data.mapper

import openweather.data.ApiBaseTest
import org.junit.Test

class ResponseMappersTest: ApiBaseTest() {

    @Test
    fun `test current weather mapper`() {
        val mapped = currentWeatherMapper.mapToModel(readCurrentWeather())
        println(mapped.toString())
    }

}