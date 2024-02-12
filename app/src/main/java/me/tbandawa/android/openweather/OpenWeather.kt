package me.tbandawa.android.openweather

import android.app.Application
import openweather.data.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class OpenWeather: Application() {

    override fun onCreate() {
        super.onCreate()

        // start koin
        initKoin {
            androidContext(this@OpenWeather)
        }

        // enable Timber in debug mode
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}