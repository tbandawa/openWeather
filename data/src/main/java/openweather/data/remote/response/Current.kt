package openweather.data.remote.response

import com.google.gson.annotations.SerializedName
import kotlin.collections.List as list

data class Current (
    @SerializedName("dt") var dt : Int,
    @SerializedName("sunrise") var sunrise : Int,
    @SerializedName("sunset") var sunset : Int,
    @SerializedName("temp") var temp : Double,
    @SerializedName("feels_like") var feelsLike : Double,
    @SerializedName("pressure") var pressure : Int,
    @SerializedName("humidity") var humidity : Int,
    @SerializedName("dew_point") var dewPoint : Double,
    @SerializedName("uvi") var uvi : Int,
    @SerializedName("clouds") var clouds : Int,
    @SerializedName("visibility") var visibility : Int,
    @SerializedName("wind_speed") var windSpeed : Double,
    @SerializedName("wind_deg") var windDeg : Int,
    @SerializedName("wind_gust") var windGust : Double,
    @SerializedName("weather") var weather : list<Weather>,
    @SerializedName("rain") var rain : Rain
)