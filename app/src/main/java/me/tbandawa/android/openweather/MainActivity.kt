package me.tbandawa.android.openweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint
import me.tbandawa.android.openweather.service.Coordinates
import me.tbandawa.android.openweather.ui.*
import openweather.data.local.PreferenceHelper
import javax.inject.Inject

@ExperimentalPermissionsApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()
            val navigateUp: () -> Unit = { navController.navigateUp() }

            val navigateToWeather: (Coordinates) -> Unit = { coordinates ->
                navController.navigate("weather/${coordinates.latitude}/${coordinates.longitude}") {
                    launchSingleTop = true
                    popUpTo("loading") { inclusive = true }
                }
            }

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
                        val viewModel = hiltViewModel<MainViewModel>()
                        WeatherContent(
                            preferenceHelper,
                            viewModel,
                            latitude!!,
                            longitude!!,
                            navigateToSettings
                        )
                    }

                    composable(route = "settings") {
                        val viewModel = hiltViewModel<MainViewModel>()
                        SettingsContent(
                            viewModel,
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