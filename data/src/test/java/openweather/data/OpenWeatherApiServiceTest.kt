package openweather.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import openweather.data.remote.api.OpenWeatherApi
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNull
import org.junit.*

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class OpenWeatherApiServiceTest {

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
    fun `test current weather api`() = runBlocking {

        enqueueResponse("current_weather.json")
        val response = service.fetchCurrentWeather("api_key", "query")
        val request = mockWebServer.takeRequest()

        assertThat(request.path, `is`("/weather?appid=api_key&q=query"))

        assertThat(response, IsNull.notNullValue())

        assertThat(response.code(), `is`(200))

        assertThat(response.body()?.base, `is`("stations"))
        assertThat(response.body()?.clouds?.all, `is`(25))

    }

    @Test
    fun `one call weather api`() = runBlocking {

        enqueueResponse("one_call_weather.json")
        val response = service.fetchOneCall("api_key", 1L, 2L)
        val request = mockWebServer.takeRequest()

        assertThat(request.path, `is`("/onecall?appid=api_key&lat=1&lon=2"))

        assertThat(response, IsNull.notNullValue())

        assertThat(response.code(), `is`(200))

        assertThat(response.body()?.timezone, `is`("Asia/Shanghai"))
        assertThat(response.body()?.current?.weather?.size, `is`(1))

    }

    @Test
    fun `five day weather api`() = runBlocking {

        enqueueResponse("five_day_weather.json")
        val response = service.fetchFiveDayWeather("api_key", "query")
        val request = mockWebServer.takeRequest()

        assertThat(request.path, `is`("/forecast?appid=api_key&q=query"))

        assertThat(response, IsNull.notNullValue())

        assertThat(response.code(), `is`(200))

        assertThat(response.body()?.list?.size, `is`(40))
        assertThat(response.body()?.city?.name, `is`("Beijing"))

    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) { mockResponse.addHeader(key, value) }
        mockWebServer.enqueue(mockResponse.setBody(source.readString(Charsets.UTF_8)))
    }

}