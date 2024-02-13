package openweather.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.List as list

@Serializable
data class CurrentWeatherResponse(
    @SerialName("coord") var coord : Coord? = null,
    @SerialName("weather") var weather : list<Weather>? = null,
    @SerialName("base") var base : String? = null,
    @SerialName("main") var main : Main? = null,
    @SerialName("visibility") var visibility : Int? = null,
    @SerialName("wind") var wind : Wind? = null,
    @SerialName("clouds") var clouds : Clouds? = null,
    @SerialName("rain") var rain : Rain? = null,
    @SerialName("snow") var snow : Snow? = null,
    @SerialName("dt") var dt : Int? = null,
    @SerialName("sys") var sys : Sys? = null,
    @SerialName("timezone") var timezone : Int? = null,
    @SerialName("id") var id : Int? = null,
    @SerialName("name") var name : String? = null,
    @SerialName("cod") var cod : Int? = null
)