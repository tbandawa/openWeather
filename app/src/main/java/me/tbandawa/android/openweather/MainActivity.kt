package me.tbandawa.android.openweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint
import me.tbandawa.android.openweather.service.LocationInfo
import me.tbandawa.android.openweather.ui.*
import openweather.data.local.PreferenceHelper
import javax.inject.Inject

@ExperimentalPermissionsApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    private val viewModel: MainViewModel by viewModels()

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            // Back navigation callback
            val navigateUp: () -> Unit = { navController.navigateUp() }

            // Weather navigation callback
            val navigateToWeather: (LocationInfo) -> Unit = { locationInfo ->
                navController.navigate("weather/${locationInfo.latitude}/${locationInfo.longitude}") {
                    launchSingleTop = true
                    popUpTo("loading") { inclusive = true }
                }
            }

            // Forecast navigation callback
            val navigateToForecast: () -> Unit = {
                navController.navigate("forecast")
            }

            // Settings navigation callback
            val navigateToSettings: () -> Unit = {
                navController.navigate("settings")
            }

            OpenWeatherTheme {
                NavHost(navController, startDestination = "loading") {

                    composable(route = "loading") {
                        LoadingContent(
                            navigateToWeather
                        )
                    }

                    composable(route = "weather/{latitude}/{longitude}") { backStackEntry ->
                        val latitude = backStackEntry.arguments?.getString("latitude")?.toDouble()
                        val longitude = backStackEntry.arguments?.getString("longitude")?.toDouble()
                        WeatherContent(
                            preferenceHelper,
                            viewModel,
                            latitude!!,
                            longitude!!,
                            navigateToSettings,
                            navigateToForecast
                        )
                    }

                    composable(route = "forecast") {
                        ForecastContent(
                            preferenceHelper.get(),
                            viewModel,
                            navigateUp
                        )
                    }

                    composable(route = "settings") {
                        SettingsContent(
                            preferenceHelper,
                            navigateUp
                        )
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
        EnableGpsContent()
    }
}