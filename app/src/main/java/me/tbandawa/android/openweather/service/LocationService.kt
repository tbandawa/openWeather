package me.tbandawa.android.openweather.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.*
import android.os.IBinder
import android.os.Bundle
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import timber.log.Timber
import java.lang.Exception
import android.location.Geocoder
import java.util.*

@SuppressLint("MissingPermission")
class LocationService(
    val context: Context
    ) : Service(), LocationListener {

    private var location: Location? = null

    private var isLocation: Boolean = false

    private var locationManager: LocationManager? = null

    val locationInfo: MutableState<LocationInfo?> = mutableStateOf(null)

    override fun onBind(arg0: Intent?): IBinder? = null

    init {
        locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager
        try {
            locationManager!!.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                (1000 * 60 * 1).toLong(), 10.toFloat(),
                this
            )
            if (locationManager != null) {
                location = locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                if (location != null) {
                    updateLocation(location!!.latitude, location!!.longitude)
                }
            }

            if (location == null) {
                locationManager!!.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    (1000 * 60 * 1).toLong(), 10.toFloat(),
                    this
                )
                if (locationManager != null) {
                    location = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    if (location != null) {
                        updateLocation(location!!.latitude, location!!.longitude)
                    }
                }
            }
        } catch (e: Exception) {
            Timber.d("ERROR -> ${e.message}")
        }
    }

    private fun updateLocation(latitude: Double, longitude: Double) {

        // Get country and city from geocoder
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        val city = addresses[0].locality
        val country = addresses[0].countryName

        val locationName: String = when {
            country.isNotEmpty() && city.isEmpty() -> { country }
            country.isEmpty() && city.isNotEmpty() -> { city }
            country.isEmpty() && city.isEmpty() -> { "open Weather" }
            else -> { "$country, $city" }
        }

        if (isLocation.not()) {
            isLocation = true
            locationInfo.value = LocationInfo(
                latitude,
                longitude,
                locationName
            )
        }

    }

    fun stopUsingGPS() { locationManager?.removeUpdates(this@LocationService) }

    override fun onLocationChanged(location: Location) {
        updateLocation(location.latitude, location.longitude)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderDisabled(provider: String) {}

    override fun onProviderEnabled(provider: String) {}

}