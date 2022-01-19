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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint
import me.tbandawa.android.openweather.ui.LoadingContent
import me.tbandawa.android.openweather.ui.PermissionContent
import me.tbandawa.android.openweather.ui.RationaleContent
import me.tbandawa.android.openweather.ui.WeatherContent
import openweather.data.local.PreferenceHelper
import openweather.domain.models.NetworkResult
import javax.inject.Inject

@ExperimentalPermissionsApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            OpenWeatherTheme {
                NavHost(navController, startDestination = "loading") {
                    composable(route = "loading") {
                        LoadingContent()
                    }
                    composable(route = "permissions") {
                        PermissionContent {}
                    }
                    composable(route = "rationale") {
                        RationaleContent()
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
        PermissionContent(launchPermissionRequest = {})
    }
}