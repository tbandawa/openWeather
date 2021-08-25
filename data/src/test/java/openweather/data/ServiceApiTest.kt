package openweather.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import openweather.data.remote.api.OpenWeatherApi
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNull
import org.junit.*

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class ServiceApiTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: OpenWeatherApi

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenWeatherApi::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun `test current weather api`() {
        enqueueResponse("current_weather.json")
        runBlocking {
            val track = service.fetchCurrentWeather("api_key", "query")
            val request = mockWebServer.takeRequest()

            assertThat(request.path, `is`("/weather?appid=api_key&q=query"))

            assertThat(track, IsNull.notNullValue())

            assertThat(track.code(), `is`(200))

            assertThat(track.body()?.base, `is`("stations"))
            assertThat(track.body()?.clouds?.all, `is`(25))
        }


    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) { mockResponse.addHeader(key, value) }
        mockWebServer.enqueue(mockResponse.setBody(source.readString(Charsets.UTF_8)))
    }

}