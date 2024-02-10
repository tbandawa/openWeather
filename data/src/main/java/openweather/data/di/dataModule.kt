package openweather.data.di

import openweather.data.local.UnitsPreferencesDataStoreImpl
import openweather.data.mapper.CurrentWeatherMapper
import openweather.data.mapper.ErrorMapper
import openweather.data.mapper.FiveDayWeatherMapper
import openweather.data.mapper.OneCallMapper
import openweather.data.remote.api.OpenWeatherApi
import openweather.data.repository.OpenWeatherRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val mappersModule = module {
    single { ErrorMapper() }
    single { OneCallMapper() }
    single { FiveDayWeatherMapper() }
    single { CurrentWeatherMapper() }
}

val apiModule = module {
    single { OpenWeatherApi() }
}

val preferencesModule = module {
    single { UnitsPreferencesDataStoreImpl(androidContext()) }
}

val repositoryModule = module {
    single { OpenWeatherRepositoryImpl(get(), get(), get(), get(), get()) }
}

val dataModule = listOf( mappersModule, apiModule, preferencesModule, repositoryModule)