package me.tbandawa.android.openweather

import android.app.Application
import me.tbandawa.android.openweather.di.viewModelModule
import openweather.data.di.apiModule
import openweather.data.di.dataModule
import openweather.data.di.mappersModule
import openweather.data.di.preferencesModule
import openweather.data.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class OpenWeather: Application() {

    override fun onCreate() {
        super.onCreate()

        // start koin
        startKoin {
            androidContext(this@OpenWeather)
            modules(
                listOf(
                    mappersModule,
                    apiModule,
                    preferencesModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }

        // enable Timber in debug mode
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

    }
}