package me.tbandawa.android.openweather.di

import me.tbandawa.android.openweather.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { MainViewModel(get()) }
}