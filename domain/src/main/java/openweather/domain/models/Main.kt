package openweather.domain.models

data class Main (
    var temp : Double?,
    var feelsLike : Double?,
    var tempMin : Double?,
    var tempMax : Double?,
    var tempKf : Double?,
    var pressure : Int?,
    var humidity : Int?,
    var seaLevel : Int?,
    var grndLevel : Int?
)