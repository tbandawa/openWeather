package me.tbandawa.android.openweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.livedata.observeAsState
import dagger.hilt.android.AndroidEntryPoint
import me.tbandawa.android.openweather.ui.WeatherContent
import me.tbandawa.android.openweather.ui.LoadingContent
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

            val preferenceUnits = preferenceHelper.observeAsState(preferenceHelper.get())
            viewModel.fetchOneCall((-26.2023).toLong(), 28.0436.toLong())
            val result = viewModel.oneCallWeather.value

            OpenWeatherTheme {
                //Surface(color = MaterialTheme.colors.background) {
                    when(result) {
                        is NetworkResult.Loading -> {
                            LoadingContent()
                        }
                        is NetworkResult.Success -> {
                            WeatherContent(oneCall = result.data!!, preferenceUnits = preferenceUnits.value)
                        }
                        is NetworkResult.Error -> {
                            Timber.d(result.message)
                        }
                    }
                //}
            }

        }
    }

}