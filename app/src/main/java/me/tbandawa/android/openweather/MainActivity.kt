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
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import java.lang.Exception

@OptIn(ExperimentalPermissionsApi::class)
@ExperimentalAnimationApi
class MainActivity : ComponentActivity(), LocationListener {

    var isGPSEnabled = false

    var isNetworkEnabled = false

    var canGetLocation = false

    var location: Location? = null

    var latitude = 0.0

    var longitude = 0.0

    private var locationManager: LocationManager? = null

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenWeatherTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val context = LocalContext.current

                    // Track if the user doesn't want to see the rationale any more.
                    var doNotShowRationale by rememberSaveable { mutableStateOf(false) }

                    // Location permission state
                    val locationPermissionState = rememberMultiplePermissionsState(
                        listOf(Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION)
                    )

                    when {
                        // If the location permission is granted, then continue to fetch location
                        locationPermissionState.allPermissionsGranted -> {
                            Timber.d("location permissions granted")

                            try {
                                locationManager = context.getSystemService(LOCATION_SERVICE)
                                        as LocationManager

                                // getting GPS status
                                isGPSEnabled = locationManager!!
                                    .isProviderEnabled(LocationManager.GPS_PROVIDER)

                                // getting network status
                                isNetworkEnabled = locationManager!!
                                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                                if (!isGPSEnabled && !isNetworkEnabled) {
                                    // no network provider is enabled
                                } else {
                                    canGetLocation = true
                                    // First get location from Network Provider
                                    if (isNetworkEnabled) {

                                        locationManager!!.requestLocationUpdates(
                                            LocationManager.NETWORK_PROVIDER,
                                            (1000 * 60 * 1).toLong(),
                                            10.toFloat(), this
                                        )
                                        Timber.d("Network")
                                        if (locationManager != null) {
                                            location = locationManager!!
                                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                                            if (location != null) {
                                                latitude = location!!.latitude
                                                longitude = location!!.longitude
                                                Timber.d("location from network: lat -> $latitude, lon -> $longitude")
                                            }
                                        }
                                    }

                                    // if GPS Enabled get lat/long using GPS Services
                                    if (isGPSEnabled) {
                                        if (location == null) {
                                            locationManager!!.requestLocationUpdates(
                                                LocationManager.GPS_PROVIDER,
                                                (1000 * 60 * 1).toLong(),
                                                10.toFloat(), this
                                            )
                                            Timber.d("GPS Enabled")
                                            if (locationManager != null) {
                                                location = locationManager!!
                                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER)
                                                if (location != null) {
                                                    latitude = location!!.latitude
                                                    longitude = location!!.longitude
                                                    Timber.d("location from gps: lat -> $latitude, lon -> $longitude")
                                                }
                                            }
                                        }
                                    }
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }


                        }

                        // If the user denied the permission but a rationale should be shown, or the user sees
                        // the permission for the first time, explain why the feature is needed by the app and allow
                        // the user to be presented with the permission again or to not see the rationale any more.
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

                        // If the criteria above hasn't been met, the user denied the permission. Let's present
                        // the user with a FAQ in case they want to know more and send them to the Settings screen
                        // to enable it the future there if they want to.
                        else -> {
                            RationaleContent()
                        }
                    }
                }
            }
        }
    }

    override fun onLocationChanged(location: Location) {}

    override fun onProviderEnabled(provider: String) {}

    override fun onProviderDisabled(provider: String) {}

}

@ExperimentalPermissionsApi
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    OpenWeatherTheme {
        PermissionContent(launchPermissionRequest = {})
    }
}