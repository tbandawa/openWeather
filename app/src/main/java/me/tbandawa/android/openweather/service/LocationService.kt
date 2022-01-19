package me.tbandawa.android.openweather.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.*
import android.os.IBinder
import android.os.Bundle
import timber.log.Timber
import java.lang.Exception

@SuppressLint("MissingPermission")
class LocationService(
    val context: Context
    ) : Service(), LocationListener {

    private var location: Location? = null

    private var latitude = 0.0

    private var longitude = 0.0

    private var isLocation: Boolean = false

    private var locationManager: LocationManager? = null

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
                    latitude = location!!.latitude
                    longitude = location!!.longitude

                    if (isLocation.not()) {
                        isLocation = true
                        Timber.d("location from network: lat -> $latitude, lon -> $longitude")
                    }

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
                        latitude = location!!.latitude
                        longitude = location!!.longitude

                        if (isLocation.not()) {
                            isLocation = true
                            Timber.d("location from gps: lat -> $latitude, lon -> $longitude")
                        }

                    }
                }
            }
        } catch (e: Exception) {
            Timber.d("ERROR -> ${e.message}")
        }
    }

    fun stopUsingGPS() { locationManager?.removeUpdates(this@LocationService) }

    override fun onLocationChanged(location: Location) {
        val lat = location.latitude
        val lng = location.longitude
        if (isLocation.not()) {
            isLocation = true
            Timber.d("on ${location.provider} location changed: lat -> $lat, lon -> $lng")
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderDisabled(provider: String) {}

    override fun onProviderEnabled(provider: String) {}

}