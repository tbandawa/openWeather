package openweather.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.List as list

@Serializable
data class Hourly (
    @SerialName("dt") var dt : Int? = null,
    @SerialName("temp") var temp : Double? = null,
    @SerialName("feels_like") var feelsLike : Double? = null,
    @SerialName("pressure") var pressure : Int? = null,
    @SerialName("humidity") var humidity : Int? = null,
    @SerialName("dew_point") var dewPoint : Double? = null,
    @SerialName("uvi") var uvi : Double? = null,
    @SerialName("clouds") var clouds : Int? = null,
    @SerialName("visibility") var visibility : Int? = null,
    @SerialName("wind_speed") var windSpeed : Double? = null,
    @SerialName("wind_deg") var windDeg : Int? = null,
    @SerialName("wind_gust") var windGust : Double? = null,
    @SerialName("weather") var weather : list<Weather>? = null,
    @SerialName("pop") var pop : Double? = null,
    @SerialName("rain") var rain : Rain? = null
)