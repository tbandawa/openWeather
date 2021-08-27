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
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.mockito.MockitoAnnotations
import org.junit.Before
import org.junit.Test
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
    fun `test get current weather`() = runBlocking {

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

    private inline fun <reified T : Any> readJsonResponse(fileName: String) : T {
        val fileContent = this::class.java.classLoader.getResource(fileName).readText()
        return Gson().fromJson(fileContent, object : TypeToken<T>() {}.type)
    }

}