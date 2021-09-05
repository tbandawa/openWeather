package openweather.data.remote.response

import com.google.gson.annotations.SerializedName
import kotlin.collections.List as list

data class Current (
    @SerializedName("dt") var dt : Int? = null,
    @SerializedName("sunrise") var sunrise : Int? = null,
    @SerializedName("sunset") var sunset : Int? = null,
    @SerializedName("temp") var temp : Double? = null,
    @SerializedName("feels_like") var feelsLike : Double? = null,
    @SerializedName("pressure") var pressure : Int? = null,
    @SerializedName("humidity") var humidity : Int? = null,
    @SerializedName("dew_point") var dewPoint : Double? = null,
    @SerializedName("uvi") var uvi : Double? = null,
    @SerializedName("clouds") var clouds : Int? = null,
    @SerializedName("visibility") var visibility : Int? = null,
    @SerializedName("wind_speed") var windSpeed : Double? = null,
    @SerializedName("wind_deg") var windDeg : Int? = null,
    @SerializedName("wind_gust") var windGust : Double? = null,
    @SerializedName("weather") var weather : list<Weather>? = null,
    @SerializedName("rain") var rain : Rain? = null,
    @SerializedName("snow") var snow : Snow? = null
)