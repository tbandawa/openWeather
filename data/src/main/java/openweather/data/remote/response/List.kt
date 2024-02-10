package openweather.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.List as list

@Serializable
data class List (
    @SerialName("dt") var dt : Int? = null,
    @SerialName("main") var main : Main? = null,
    @SerialName("weather") var weather : list<Weather>? = null,
    @SerialName("clouds") var clouds : Clouds? = null,
    @SerialName("wind") var wind : Wind? = null,
    @SerialName("visibility") var visibility : Int? = null,
    @SerialName("pop") var pop : Double? = null,
    @SerialName("rain") var rain : Rain? = null,
    @SerialName("snow") var snow : Snow? = null,
    @SerialName("sys") var sys : Sys? = null,
    @SerialName("dt_txt") var dtTxt : String? = null
)