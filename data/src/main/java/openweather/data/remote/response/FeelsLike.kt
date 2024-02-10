package openweather.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeelsLike (
    @SerialName("day") var day : Double? = null,
    @SerialName("night") var night : Double? = null,
    @SerialName("eve") var eve : Double? = null,
    @SerialName("morn") var morn : Double? = null
)