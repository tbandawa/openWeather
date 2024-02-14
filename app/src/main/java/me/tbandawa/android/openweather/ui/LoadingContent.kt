package me.tbandawa.android.openweather.ui

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import me.tbandawa.android.openweather.service.LocationInfo
import me.tbandawa.android.openweather.service.LocationService

@ExperimentalPermissionsApi
@Composable
fun LoadingContent(
    navigateToWeather: (LocationInfo) -> Unit
){

    val context = LocalContext.current

    val doNotShowRationale by rememberSaveable { mutableStateOf(false) }

    // Create permission state or required permissions
    val locationPermissionState = rememberMultiplePermissionsState(
        listOf(Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION)
    )

    when {
        // Permissions granted
        locationPermissionState.allPermissionsGranted -> {
            // Create location service and observe gps coordinates and navigate
            // to weather content or else prompt user to enable device location services
            val locationInfo = LocationService(context).apply {
                getLocation()
            }.locationInfo.collectAsState()

            if (locationInfo.value != null) {
                navigateToWeather(locationInfo.value!!)
            } else {
                EnableGpsContent()
            }
        }
        //Either location services no available or requested permissions not granted
        locationPermissionState.shouldShowRationale ||
                !locationPermissionState.permissionRequested -> {
            if (doNotShowRationale) {
                NoGpsContent()
            } else {
                // Ask user to grant permissions
                PermissionContent {
                    locationPermissionState.launchMultiplePermissionRequest()
                }
            }
        }
        // User denied permissions, request explicit permission access
        else -> {
            RationaleContent()
        }
    }
}