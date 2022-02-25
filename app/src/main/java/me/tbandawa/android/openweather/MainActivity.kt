package me.tbandawa.android.openweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.tbandawa.android.openweather.service.LocationInfo
import me.tbandawa.android.openweather.ui.*
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme
import openweather.domain.datastore.UnitsPreferencesDataStore
import openweather.domain.models.PreferenceUnits
import timber.log.Timber
import javax.inject.Inject

@ExperimentalPermissionsApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var unitsPreferencesDataStore: UnitsPreferencesDataStore

    private val viewModel: MainViewModel by viewModels()

    @OptIn(InternalCoroutinesApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            unitsPreferencesDataStore.preferencesUnits.collect {
                Timber.d("collecting -> $it")
            }
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
                            preferenceUnits,
                            viewModel,
                            latitude!!,
                            longitude!!,
                            location!!,
                            navigateToSettings
                        ) { navigateToForecast(location) }
                    }

                    composable(route = "forecast/{location}") { backStackEntry ->
                        val location = backStackEntry.arguments?.getString("location")
                        ForecastContent(
                            preferenceUnits,
                            viewModel,
                            location!!,
                            navigateUp
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