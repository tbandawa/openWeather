package me.tbandawa.android.openweather

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint
import me.tbandawa.android.openweather.ui.*

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
                    composable(route = "weather") {

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