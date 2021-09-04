package me.tbandawa.android.openweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import me.tbandawa.android.openweather.ui.components.MainContent
import me.tbandawa.android.openweather.ui.components.MainToolBar
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme
import openweather.domain.models.NetworkResult
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            when(val result = viewModel.currentWeather.value) {
                is NetworkResult.Loading -> {
                    Timber.d("loading...")
                }
                is NetworkResult.Success -> {
                    Timber.d(result.data.toString())
                }
                is NetworkResult.Error -> {
                    Timber.d(result.message)
                }
            }

            OpenWeatherTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = { MainToolBar() }
                    ) {
                        MainContent()
                    }
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    OpenWeatherTheme {
        MainContent()
    }
}