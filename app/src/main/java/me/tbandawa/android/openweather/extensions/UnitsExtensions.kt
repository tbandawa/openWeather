package me.tbandawa.android.openweather.extensions

import kotlin.math.roundToInt

fun Double.toTemperature(unit: String) : String {
    return when (unit) {
        "°C" -> {
            var celcius = this - 273.15
            "${celcius.roundToInt()}°C"
        }
        "°F" -> {
            var fahrenheit = (9/5*(this - 273)) + 32
            "${fahrenheit.roundToInt()}°F"
        }
        else -> {""}
    }
}

fun Double.toSpeed(unit: String) : String {
    return "toSpeed"
}

fun Int.toDirection() : String = "${this}°"

fun Int.toPressure(unit: String) : String {
    return "toPressure"
}

fun Double.toDewPoint(unit: String) : String {
    return when (unit) {
        "°C" -> {
            var celcius = this - 273.15
            "${celcius.roundToInt()}°C"
        }
        "°F" -> {
            var fahrenheit = (9/5*(this - 273)) + 32
            "${fahrenheit.roundToInt()}°F"
        }
        else -> {""}
    }
}

fun Int.toVisibility() : String = "${this}m"

fun Int.toCloudCover() : String = "$this%"

fun Int.toHumidity() : String = "$this%"

fun Double.toUV() : String = "${this.toInt()}"