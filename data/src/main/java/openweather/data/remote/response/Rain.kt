package openweather.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rain(
    @SerialName("1h") var one_h : Double? = null,
    @SerialName("3h") var three_h : Double? = null
)
