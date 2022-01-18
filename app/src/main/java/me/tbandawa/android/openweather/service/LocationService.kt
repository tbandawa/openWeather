package me.tbandawa.android.openweather.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.os.IBinder
import android.location.LocationManager
import android.os.Bundle
import timber.log.Timber
import java.lang.Exception

class LocationService(val context: Context) : Service(), LocationListener {

    var isGPSEnabled = false

    var isNetworkEnabled = false

    var canGetLocation = false

    var location: Location? = null

    var latitude = 0.0

    var longitude = 0.0

    private val MIN_DISTANCE_CHANGE_FOR_UPDATES: Long = 10

    private val MIN_TIME_BW_UPDATES = (1000 * 60 * 1).toLong()

    protected var locationManager: LocationManager? = null

    init { getGeoLocation() }

    @SuppressLint("MissingPermission")
    fun getGeoLocation(): Location? {
        try {
            locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager

            // getting GPS status
            isGPSEnabled = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)

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
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this
                    )
                    Timber.d("Network")
                    if (locationManager != null) {
                        location = locationManager!!
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                        if (location != null) {
                            latitude = location!!.latitude
                            longitude = location!!.longitude
                        }
                    }
                }

                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager!!.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this
                        )
                        Timber.d("GPS Enabled")
                        if (locationManager != null) {
                            location = locationManager!!
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER)
                            if (location != null) {
                                latitude = location!!.latitude
                                longitude = location!!.longitude
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return location
    }


    fun stopUsingGPS() { locationManager?.removeUpdates(this@LocationService) }

    fun canGetLocation(): Boolean = canGetLocation

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onBind(arg0: Intent?): IBinder? = null

    override fun onLocationChanged(location: Location) {}

    override fun onProviderEnabled(provider: String) {}

    override fun onProviderDisabled(provider: String) {}

}