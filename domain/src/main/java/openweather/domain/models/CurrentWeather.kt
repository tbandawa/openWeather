package openweather.domain.models

import kotlin.collections.List as list

data class CurrentWeather(
    var coord : Coord?,
    var weather : list<Weather>?,
    var base : String?,
    var main : Main?,
    var visibility : Int?,
    var wind : Wind?,
    var clouds : Clouds?,
    var rain : Rain?,
    var snow : Snow?,
    var dt : Int?,
    var sys : Sys?,
    var timezone : Int?,
    var id : Int?,
    var name : String?,
    var cod : Int?
)