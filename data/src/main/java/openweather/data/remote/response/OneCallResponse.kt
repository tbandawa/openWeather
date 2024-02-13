package openweather.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.List as list

@Serializable
data class OneCallResponse (
    @SerialName("lat") var lat : Double? = null,
    @SerialName("lon") var lon : Double? = null,
    @SerialName("timezone") var timezone : String? = null,
    @SerialName("timezone_offset") var timezoneOffset : Int? = null,
    @SerialName("current") var current : Current? = null,
    @SerialName("minutely") var minutely : list<Minutely>? = null,
    @SerialName("hourly") var hourly : list<Hourly>? = null,
    @SerialName("daily") var daily : list<Daily>? = null
)