package me.tbandawa.android.openweather.extensions

fun Double.toTemperature() : String {
    return "toTemperature"
}

fun Double.toSpeed() : String {
    return "toSpeed"
}

fun Int.toDirection() : String {
    return "toDirection"
}

fun Int.toPressure() : String {
    return "toPressure"
}

fun Double.toDewPoint() : String {
    return "toDewPoint"
}

fun Int.toVisibility() : String {
    return "toVisibility"
}

fun Int.toCloudCover() : String = "$this%"

fun Int.toHumidity() : String = "$this%"

fun Double.toUV() : String = "${this.toInt()}"