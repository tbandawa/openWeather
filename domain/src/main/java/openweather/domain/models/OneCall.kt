package openweather.domain.models

import kotlin.collections.List as list

data class OneCall (
    var lat : Double?,
    var lon : Double?,
    var timezone : String?,
    var timezoneOffset : Int?,
    var current : Current?,
    var minutely : list<Minutely>?,
    var hourly : list<Hourly>?,
    var daily : list<Daily>?
)