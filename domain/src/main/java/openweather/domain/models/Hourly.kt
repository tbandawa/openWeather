package openweather.domain.models

import kotlin.collections.List as list

data class Hourly (
    var dt : Int?,
    var temp : Double?,
    var feelsLike : Double?,
    var pressure : Int?,
    var humidity : Int?,
    var dewPoint : Double?,
    var uvi : Double?,
    var clouds : Int?,
    var visibility : Int?,
    var windSpeed : Double?,
    var windDeg : Int?,
    var windGust : Double?,
    var weather : list<Weather>?,
    var pop : Double?,
    var rain : Rain?
)