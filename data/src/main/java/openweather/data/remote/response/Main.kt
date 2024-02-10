package openweather.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Main (
    @SerialName("temp") var temp : Double? = null,
    @SerialName("feels_like") var feelsLike : Double? = null,
    @SerialName("temp_min") var tempMin : Double? = null,
    @SerialName("temp_max") var tempMax : Double? = null,
    @SerialName("temp_kf") var tempKf : Double? = null,
    @SerialName("pressure") var pressure : Int? = null,
    @SerialName("humidity") var humidity : Int? = null,
    @SerialName("sea_level") var seaLevel : Int? = null,
    @SerialName("grnd_level") var grndLevel : Int? = null
)