package me.tbandawa.android.openweather.extensions

import kotlin.math.roundToInt
import java.text.SimpleDateFormat
import java.util.*

fun Double.toTemperature(unit: String) : String {
    return when (unit) {
        "°C" -> {
            var celcius = this - 273.15
            "${celcius.roundToInt()} °C"
        }
        "°F" -> {
            var fahrenheit = (9/5*(this - 273)) + 32
            "${fahrenheit.roundToInt()} °F"
        }
        else -> {""}
    }
}

fun Double.toSpeed(unit: String) : String {
    return when (unit) {
        "m/s" -> "${this.roundToInt()} m/s"
        "km/h" -> {
            var speed = this*3.6
            "${speed.roundToInt()} km/h"
        }
        "mph" -> {
            var speed = this*2.23694
            "${speed.roundToInt()} mph"
        }
        else -> {""}
    }
}

fun Int.toDirection() : String = "${this}°"

fun Int.toPressure(unit: String) : String {
    return when (unit) {
        "hPa" -> "$this hPa"
        "inHg" -> {
            var pressure = this*0.029529983071445
            "${pressure.roundToInt()} inHg"
        }
        else -> {""}
    }
}

fun Double.toDewPoint(unit: String) : String {
    return when (unit) {
        "°C" -> {
            var celcius = this - 273.15
            "${celcius.roundToInt()} °C"
        }
        "°F" -> {
            var fahrenheit = (9/5*(this - 273)) + 32
            "${fahrenheit.roundToInt()} °F"
        }
        else -> {""}
    }
}

fun Int.toVisibility() : String = "$this m"

fun Int.toCloudCover() : String = "$this %"

fun Int.toHumidity() : String = "$this %"

fun Double.toUV() : String = "${this.toInt()}"

fun Int.toTime(unit: String) : String {

    val seconds = this*1000L
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = seconds

    return when (unit) {
        "24-hour" -> {
            val formatter = SimpleDateFormat("HH:mm")
            formatter.format(calendar.time)
        }
        "12-hour" -> {
            val formatter = SimpleDateFormat("hh:mm aaa")
            formatter.format(calendar.time)
        }
        else -> {""}
    }
}

fun Int.toDay() : String {
    val seconds = this*1000L
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = seconds
    val formatter = SimpleDateFormat("EEE")
    return formatter.format(calendar.time)
}

fun Int.toDate() : String {
    val seconds = this*1000L
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = seconds
    val formatter = SimpleDateFormat("EEE, d MMM yyyy")
    return formatter.format(calendar.time)
}