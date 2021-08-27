package openweather.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import openweather.data.remote.api.OpenWeatherApi
import openweather.data.remote.response.*
import openweather.domain.models.CurrentWeather
import openweather.domain.models.FiveDayWeatherForecast
import openweather.domain.models.NetworkResult
import openweather.domain.models.OneCall
import openweather.domain.repository.OpenWeatherRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.mockito.MockitoAnnotations
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mock
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import retrofit2.Response

open class OpenWeatherRepositoryTest {

    @Mock
    lateinit var openWeatherApi: OpenWeatherApi

    private lateinit var openWeatherRepository: OpenWeatherRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        openWeatherRepository = OpenWeatherRepositoryImpl(openWeatherApi)
    }

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

        `when`(openWeatherApi.fetchOneCall(anyString(), anyLong(), anyLong())).thenReturn(Response.success(oneCallResponse))

        openWeatherRepository.fetchOneCall(1L, 1L).collect { value: NetworkResult<OneCall> ->

            if(value is NetworkResult.Success) {
                MatcherAssert.assertThat(
                    value.data?.current?.sunrise,
                    CoreMatchers.`is`(1629927390)
                )
            }

        }

    }

    private inline fun <reified T : Any> readJsonResponse(fileName: String) : T {
        val fileContent = this::class.java.classLoader.getResource(fileName).readText()
        return Gson().fromJson(fileContent, object : TypeToken<T>() {}.type)
    }

}