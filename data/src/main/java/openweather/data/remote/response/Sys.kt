package openweather.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sys (
    @SerialName("type") var type : Int? = null,
    @SerialName("id") var id : Int? = null,
    @SerialName("message") var message : String? = null,
    @SerialName("country") var country : String? = null,
    @SerialName("sunrise") var sunrise : Int? = null,
    @SerialName("sunset") var sunset : Int? = null,
    @SerialName("pod") var pod : String? = null
)