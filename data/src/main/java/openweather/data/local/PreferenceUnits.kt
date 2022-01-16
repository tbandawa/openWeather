package openweather.data.local

data class PreferenceUnits(
    var temperature: String = "°C",
    var speed: String = "m/s",
    var pressure: String = "hPa",
    var distance: String = "m",
    var time: String = "24-hour"
)
