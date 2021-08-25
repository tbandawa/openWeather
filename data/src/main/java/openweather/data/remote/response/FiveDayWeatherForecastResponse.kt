package openweather.data.remote.response

import com.google.gson.annotations.SerializedName
import kotlin.collections.List as list

data class FiveDayWeatherForecastResponse (
    @SerializedName("cod") var cod : String,
    @SerializedName("message") var message : Int,
    @SerializedName("cnt") var cnt : Int,
    @SerializedName("list") var list : list<List>,
    @SerializedName("city") var city : City
)