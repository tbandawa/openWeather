package me.tbandawa.android.openweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.livedata.observeAsState
import dagger.hilt.android.AndroidEntryPoint
import me.tbandawa.android.openweather.ui.components.LoadingContent
import me.tbandawa.android.openweather.ui.components.MainContent
import me.tbandawa.android.openweather.ui.components.MainToolBar
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme
import openweather.data.local.PreferenceHelper
import openweather.domain.models.NetworkResult
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class WeatherActivity : ComponentActivity() {

    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    private val viewModel: WeatherViewModel by viewModels()

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Timber.d("${preferenceHelper.observeAsState(preferenceHelper.get()).value}")
            val preferenceUnits = preferenceHelper.observeAsState(preferenceHelper.get())

            //viewModel.fetchOneCall((-26.2023).toLong(), 28.0436.toLong())
            val result = viewModel.oneCallWeather.value

            OpenWeatherTheme {
                Surface(color = MaterialTheme.colors.background) {
                    when(result) {
                        is NetworkResult.Loading -> {
                            LoadingContent()
                        }
                        is NetworkResult.Success -> {
                            Scaffold(
                                topBar = { MainToolBar() }
                            ) {
                                MainContent(result.data!!, preferenceUnits.value)
                            }
                        }
                        is NetworkResult.Error -> {
                            Timber.d(result.message)
                        }
                    }
                }
            }

        }
    }

}