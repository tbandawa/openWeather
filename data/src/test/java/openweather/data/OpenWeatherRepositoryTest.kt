package openweather.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.runBlocking
import openweather.data.remote.api.OpenWeatherApi
import openweather.data.remote.response.*
import openweather.data.repository.OpenWeatherRepositoryImpl
import openweather.domain.repository.OpenWeatherRepository
import org.mockito.MockitoAnnotations
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class OpenWeatherRepositoryTest {

    @Mock
    lateinit var openWeatherApi: OpenWeatherApi

    lateinit var openWeatherRepository: OpenWeatherRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        openWeatherRepository = OpenWeatherRepositoryImpl(openWeatherApi)
    }

    @Test
    fun `test get current weather`() = runBlocking {

        val response = readCurrentWeather()
        println(response.toString())

    }

    private fun readCurrentWeather(): CurrentWeatherResponse {
        val fileContent =
            this::class.java.classLoader.getResource("current_weather.json").readText()
        return Gson().fromJson(fileContent, object : TypeToken<CurrentWeatherResponse>() {}.type)
    }

}