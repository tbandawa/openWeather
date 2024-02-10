package openweather.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.List as list

@Serializable
data class FiveDayWeatherForecastResponse (
    @SerialName("cod") var cod : String? = null,
    @SerialName("message") var message : Int? = null,
    @SerialName("cnt") var cnt : Int? = null,
    @SerialName("list") var list : list<List>? = null,
    @SerialName("city") var city : City? = null
)