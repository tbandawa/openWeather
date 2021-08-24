package openweather.domain.models

import kotlin.collections.List as list

data class FiveDayWeatherForecastResponse (
    var cod : String?,
    var message : Int?,
    var cnt : Int?,
    var list : list<List>?,
    var city : City?
)