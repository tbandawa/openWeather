package openweather.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Minutely (
    @SerialName("dt") var dt : Int? = null,
    @SerialName("precipitation") var precipitation : Double? = null
)