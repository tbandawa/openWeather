package me.tbandawa.android.openweather

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
import com.google.accompanist.permissions.rememberPermissionState
import me.tbandawa.android.openweather.ui.theme.OpenWeatherTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import me.tbandawa.android.openweather.ui.components.PermissionContent
import me.tbandawa.android.openweather.ui.components.RationaleContent
import timber.log.Timber

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenWeatherTheme {
                Surface(color = MaterialTheme.colors.background) {
                    RequireLocationPermission()
                }
            }
        }
    }

}

@OptIn(ExperimentalPermissionsApi::class)
@ExperimentalAnimationApi
@Composable
private fun RequireLocationPermission() {
    // Track if the user doesn't want to see the rationale any more.
    var doNotShowRationale by rememberSaveable { mutableStateOf(false) }

    // Location permission state
    val locationPermissionState = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    when {
        // If the location permission is granted, then continue to fetch location
        locationPermissionState.hasPermission -> {
            Timber.d("location granted")
        }

        // If the user denied the permission but a rationale should be shown, or the user sees
        // the permission for the first time, explain why the feature is needed by the app and allow
        // the user to be presented with the permission again or to not see the rationale any more.
        locationPermissionState.shouldShowRationale ||
                !locationPermissionState.permissionRequested -> {
            if (doNotShowRationale) {
                Timber.d("Feature not available")
            } else {
                PermissionContent { locationPermissionState.launchPermissionRequest() }
            }
        }

        // If the criteria above hasn't been met, the user denied the permission. Let's present
        // the user with a FAQ in case they want to know more and send them to the Settings screen
        // to enable it the future there if they want to.
        else -> {
            RationaleContent()
        }
    }
}

@ExperimentalPermissionsApi
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    OpenWeatherTheme {
        RationaleContent()
    }
}