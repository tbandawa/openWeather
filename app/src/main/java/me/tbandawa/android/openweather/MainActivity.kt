package me.tbandawa.android.openweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import me.tbandawa.android.openweather.service.LocationInfo
import me.tbandawa.android.openweather.service.LocationService
import me.tbandawa.android.openweather.ui.EnableGpsContent
import me.tbandawa.android.openweather.ui.ForecastContent
import me.tbandawa.android.openweather.ui.LoadingContent
import me.tbandawa.android.openweather.ui.SettingsContent
import me.tbandawa.android.openweather.ui.WeatherContent
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme
import openweather.data.viewmodels.MainViewModel
import openweather.domain.datastore.UnitsPreferencesDataStore
import openweather.domain.models.PreferenceUnits
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalPermissionsApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    private val unitsPreferencesDataStore: UnitsPreferencesDataStore by inject()
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create location service, observe gps coordinates and
        // navigate call api if location info is available
        LocationService(this@MainActivity).locationInfo.observe(this) { locationInfo ->
            if (locationInfo != null) {
                viewModel.fetchOneCall(
                    lat = locationInfo.latitude,
                    lon = locationInfo.longitude
                )
            } else {
                viewModel.dismissSplash()
            }
        }

        installSplashScreen().setKeepOnScreenCondition {
            viewModel.showSplash.value
        }

        setContent {

            val preferenceUnits = unitsPreferencesDataStore.preferencesUnits.collectAsState(
                PreferenceUnits("°C", "m/s", "hPa", "12-hour")
            ).value

            val navController = rememberNavController()

            // Back navigation callback
            val navigateUp: () -> Unit = { navController.navigateUp() }

            // Weather navigation callback
            val navigateToWeather: (LocationInfo) -> Unit = { locationInfo ->
                navController.navigate("weather/${locationInfo.latitude}/${locationInfo.longitude}/${locationInfo.location}") {
                    launchSingleTop = true
                    popUpTo("loading") { inclusive = true }
                }
            }

            // Forecast navigation callback
            val navigateToForecast: (location: String) -> Unit = { location ->
                navController.navigate("forecast/${location}")
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

                    composable(route = "weather/{latitude}/{longitude}/{location}") { backStackEntry ->
                        val latitude = backStackEntry.arguments?.getString("latitude")?.toDouble()
                        val longitude = backStackEntry.arguments?.getString("longitude")?.toDouble()
                        val location = backStackEntry.arguments?.getString("location")
                        WeatherContent(
                            preferenceUnits = preferenceUnits,
                            viewModel = viewModel,
                            latitude = latitude!!,
                            longitude = longitude!!,
                            location = location!!,
                            navigateToSettings =  { navigateToSettings() },
                            navigateToForecast = { navigateToForecast(location) }
                        )
                    }

                    composable(route = "forecast/{location}") { backStackEntry ->
                        val location = backStackEntry.arguments?.getString("location")
                        ForecastContent(
                            preferenceUnits = preferenceUnits,
                            viewModel = viewModel,
                            location = location!!,
                            navigateUp = navigateUp
                        )
                    }

                    composable(route = "settings") {
                        SettingsContent(
                            unitsPreferencesDataStore,
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