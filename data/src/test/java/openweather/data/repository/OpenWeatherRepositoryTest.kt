package openweather.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import openweather.data.remote.api.OpenWeatherApi
import openweather.data.remote.response.*
import openweather.domain.models.CurrentWeather
import openweather.domain.models.NetworkResult
import openweather.domain.repository.OpenWeatherRepository
import org.mockito.MockitoAnnotations
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import retrofit2.Response


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

        /*val currentWeatherResponse = readJsonResponse<Any>("current_weather.json")

        `when`(openWeatherApi.fetchCurrentWeather(anyString(), anyString())).thenReturn(Response.success(currentWeatherResponse))

        openWeatherRepository.fetchCurrentWeather("city").collect { value: NetworkResult<CurrentWeather> ->

            when(value) {

                is NetworkResult.Loading -> {
                    println("loading...")
                }
                is NetworkResult.Success -> {
                    println("success -> ${value.data.toString()}")
                }
                is NetworkResult.Error -> {
                    println("error -> ${value.message}")
                }
            }

        }*/

    }

}