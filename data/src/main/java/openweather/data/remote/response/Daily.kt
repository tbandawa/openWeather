package openweather.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.List as list

@Serializable
data class Daily (
    @SerialName("dt") var dt : Int? = null,
    @SerialName("sunrise") var sunrise : Int? = null,
    @SerialName("sunset") var sunset : Int? = null,
    @SerialName("moonrise") var moonrise : Int? = null,
    @SerialName("moonset") var moonset : Int? = null,
    @SerialName("moon_phase") var moonPhase : Double? = null,
    @SerialName("temp") var temp : Temp? = null,
    @SerialName("feels_like") var feelsLike : FeelsLike? = null,
    @SerialName("pressure") var pressure : Int? = null,
    @SerialName("humidity") var humidity : Int? = null,
    @SerialName("dew_point") var dewPoint : Double? = null,
    @SerialName("wind_speed") var windSpeed : Double? = null,
    @SerialName("wind_deg") var windDeg : Int? = null,
    @SerialName("wind_gust") var windGust : Double? = null,
    @SerialName("weather") var weather : list<Weather>? = null,
    @SerialName("clouds") var clouds : Int? = null,
    @SerialName("pop") var pop : Double? = null,
    @SerialName("rain") var rain : Double? = null,
    @SerialName("uvi") var uvi : Double? = null
)