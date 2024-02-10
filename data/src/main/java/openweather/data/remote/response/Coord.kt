package openweather.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Coord (
    @SerialName("lon") var lon : Double? = null,
    @SerialName("lat") var lat : Double? = null
)