package me.tbandawa.android.openweather

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import me.tbandawa.android.openweather.ui.components.PermissionContent
import me.tbandawa.android.openweather.ui.components.RationaleContent
import timber.log.Timber
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import me.tbandawa.android.openweather.service.LocationService

@OptIn(ExperimentalPermissionsApi::class)
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenWeatherTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val context = LocalContext.current

                    var doNotShowRationale by rememberSaveable { mutableStateOf(false) }

                    val locationPermissionState = rememberMultiplePermissionsState(
                        listOf(Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION)
                    )

                    when {
                        locationPermissionState.allPermissionsGranted -> {
                            LocationService(context)
                        }

                        locationPermissionState.shouldShowRationale ||
                                !locationPermissionState.permissionRequested -> {
                            if (doNotShowRationale) {
                                Timber.d("Feature not available")
                            } else {
                                PermissionContent {
                                    locationPermissionState.launchMultiplePermissionRequest()
                                }
                            }
                        }

                        else -> {
                            RationaleContent()
                        }

                    }
                }
            }
        }
    }

}

@ExperimentalPermissionsApi
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    OpenWeatherTheme {
        PermissionContent(launchPermissionRequest = {})
    }
}