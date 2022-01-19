package me.tbandawa.android.openweather

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.livedata.observeAsState
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint
import me.tbandawa.android.openweather.ui.WeatherContent
import me.tbandawa.android.openweather.ui.components.*
import openweather.data.local.PreferenceHelper
import openweather.domain.models.NetworkResult
import javax.inject.Inject

@ExperimentalPermissionsApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    private val viewModel: WeatherViewModel by viewModels()

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val preferenceUnits = preferenceHelper.observeAsState(preferenceHelper.get())
            viewModel.fetchOneCall((-20.1837).toLong(), 28.5203.toLong())

            OpenWeatherTheme {
                when(val result = viewModel.oneCallWeather.value) {
                    is NetworkResult.Loading -> {
                        LoadingContent()
                    }
                    is NetworkResult.Success -> {
                        WeatherContent(result.data!!, preferenceUnits.value)
                    }
                    is NetworkResult.Error -> {

                    }
                }
            }

        }
    }

}

@ExperimentalPermissionsApi
@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    OpenWeatherTheme {
        LoadingContent()
    }
}