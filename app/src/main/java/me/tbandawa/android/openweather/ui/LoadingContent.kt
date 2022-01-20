package me.tbandawa.android.openweather.ui

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import me.tbandawa.android.openweather.service.Coordinates
import me.tbandawa.android.openweather.service.LocationService

@ExperimentalPermissionsApi
@Composable
fun LoadingContent(
    navigateToWeather: (Coordinates) -> Unit
){

    val context = LocalContext.current

    var doNotShowRationale by rememberSaveable { mutableStateOf(false) }

    val locationPermissionState = rememberMultiplePermissionsState(
        listOf(Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION)
    )

    when {
        locationPermissionState.allPermissionsGranted -> {
            val locationService = LocationService(context)
            locationService.coordinates.value?.let { coordinates ->
                navigateToWeather(coordinates)
            } ?: run {
                EnableGpsContent()
            }
        }
        locationPermissionState.shouldShowRationale ||
                !locationPermissionState.permissionRequested -> {
            if (doNotShowRationale) {
                NoGpsContent()
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