package openweather.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import openweather.data.BuildConfig
import openweather.data.local.UnitsPreferencesDataStoreImpl
import openweather.data.mapper.CurrentWeatherMapper
import openweather.data.mapper.FiveDayWeatherMapper
import openweather.data.mapper.OneCallMapper
import openweather.data.remote.api.OpenWeatherApi
import openweather.data.remote.response.CurrentWeatherResponse
import openweather.data.remote.response.FiveDayWeatherForecastResponse
import openweather.data.remote.response.OneCallResponse
import openweather.data.repository.OpenWeatherRepositoryImpl
import openweather.domain.datastore.UnitsPreferencesDataStore
import openweather.domain.mapper.ResponseMapper
import openweather.domain.models.CurrentWeather
import openweather.domain.models.FiveDayWeatherForecast
import openweather.domain.models.OneCall
import openweather.domain.repository.OpenWeatherRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private val okHttpClient: OkHttpClient by lazy {
        val interceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY else
                HttpLoggingInterceptor.Level.NONE
        }
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .build()
    }

    private val baseUrl: String by lazy {
        BuildConfig.OPEN_WEATHER_BASE_URL
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideUnitsPreferencesDataStore(
        @ApplicationContext appContext: Context
    ): UnitsPreferencesDataStore = UnitsPreferencesDataStoreImpl(appContext)

    @Provides
    @Singleton
    fun provideOpenWeatherApi(): OpenWeatherApi = retrofit.create(OpenWeatherApi::class.java)

    @Provides
    @Singleton
    fun provideCurrentWeatherMapper(): ResponseMapper<CurrentWeatherResponse, CurrentWeather> = CurrentWeatherMapper()

    @Provides
    @Singleton
    fun provideOneCallMapper(): ResponseMapper<OneCallResponse, OneCall> = OneCallMapper()

    @Provides
    @Singleton
    fun provideFiveDayWeatherMapper(): ResponseMapper<FiveDayWeatherForecastResponse, FiveDayWeatherForecast> = FiveDayWeatherMapper()

    @Provides
    @Singleton
    fun provideOpenWeatherRepository(
        openWeatherApi: OpenWeatherApi,
        currentWeatherMapper: ResponseMapper<CurrentWeatherResponse, CurrentWeather>,
        oneCallMapper: ResponseMapper<OneCallResponse, OneCall>,
        fiveDayWeatherMapper: ResponseMapper<FiveDayWeatherForecastResponse, FiveDayWeatherForecast>
    ) : OpenWeatherRepository = OpenWeatherRepositoryImpl(openWeatherApi, currentWeatherMapper, oneCallMapper, fiveDayWeatherMapper)

}