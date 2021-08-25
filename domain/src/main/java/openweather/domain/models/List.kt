package openweather.domain.models

import kotlin.collections.List as list

data class List (
    var dt : Int?,
    var main : Main?,
    var weather : list<Weather>?,
    var clouds : Clouds?,
    var wind : Wind?,
    var visibility : Int?,
    var pop : Double?,
    var rain : Rain?,
    var sys : Sys?,
    var dtTxt : String?
)