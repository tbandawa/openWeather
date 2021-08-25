package openweather.domain.models

import kotlin.collections.List as list

data class Daily (
    var dt : Int?,
    var sunrise : Int?,
    var sunset : Int?,
    var moonrise : Int?,
    var moonset : Int?,
    var moonPhase : Double?,
    var temp : Temp?,
    var feelsLike : FeelsLike?,
    var pressure : Int?,
    var humidity : Int?,
    var dewPoint : Double?,
    var windSpeed : Double?,
    var windDeg : Int?,
    var windGust : Double?,
    var weather : list<Weather>?,
    var clouds : Int?,
    var pop : Double?,
    var rain : Double?,
    var uvi : Double?
)