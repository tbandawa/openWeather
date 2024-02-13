package openweather.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Temp (
    @SerialName("day") var day : Double? = null,
    @SerialName("min") var min : Double? = null,
    @SerialName("max") var max : Double? = null,
    @SerialName("night") var night : Double? = null,
    @SerialName("eve") var eve : Double? = null,
    @SerialName("morn") var morn : Double? = null
)