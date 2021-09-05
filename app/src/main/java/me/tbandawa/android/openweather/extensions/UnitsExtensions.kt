package me.tbandawa.android.openweather.extensions

import android.content.Context

fun Double.toTemperature(context: Context) : String {
    return "toTemperature"
}

fun Double.toSpeed(context: Context) : String {
    return "toSpeed"
}

fun Int.toDirection(context: Context) : String {
    return "toDirection"
}

fun Int.toPressure(context: Context) : String {
    return "toPressure"
}

fun Double.toDewPoint(context: Context) : String {
    return "toDewPoint"
}

fun Int.toVisibility(context: Context) : String {
    return "toVisibility"
}

fun Int.toCloudCover() : String = "$this%"

fun Int.toHumidity() : String = "$this%"

fun Double.toUV() : String = "${this.toInt()}"