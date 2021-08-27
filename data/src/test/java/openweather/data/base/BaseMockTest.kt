package openweather.data.base

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import openweather.data.remote.api.OpenWeatherApi
import openweather.data.repository.OpenWeatherRepositoryImpl
import openweather.domain.repository.OpenWeatherRepository
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

abstract class BaseMockTest {

    @Mock
    lateinit var openWeatherApi: OpenWeatherApi

    protected lateinit var openWeatherRepository: OpenWeatherRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        openWeatherRepository = OpenWeatherRepositoryImpl(openWeatherApi)
    }

    protected inline fun <reified T : Any> readJsonResponse(fileName: String) : T {
        val fileContent = this::class.java.classLoader.getResource(fileName).readText()
        return Gson().fromJson(fileContent, object : TypeToken<T>() {}.type)
    }

}