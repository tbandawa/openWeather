package openweather.data.remote.response

import com.google.gson.annotations.SerializedName

data class Hourly (
    @SerializedName("dt") var dt : Int,
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
    @SerializedName("weather") var weather : List<Weather>,
    @SerializedName("pop") var pop : Double,
    @SerializedName("rain") var rain : Rain
)