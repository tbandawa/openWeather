package openweather.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Wind (
    @SerialName("speed") var speed : Double? = null,
    @SerialName("deg") var deg : Int? = null,
    @SerialName("gust") var gust : Double? = null
)