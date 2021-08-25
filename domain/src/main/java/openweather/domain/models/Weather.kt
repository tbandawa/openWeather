package openweather.domain.models

data class Weather (
    var id : Int?,
    var main : String?,
    var description : String?,
    var icon : String?
)