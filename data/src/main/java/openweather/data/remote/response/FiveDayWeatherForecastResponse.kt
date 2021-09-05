package openweather.data.remote.response

import com.google.gson.annotations.SerializedName
import kotlin.collections.List as list

data class FiveDayWeatherForecastResponse (
    @SerializedName("cod") var cod : String? = null,
    @SerializedName("message") var message : Int? = null,
    @SerializedName("cnt") var cnt : Int? = null,
    @SerializedName("list") var list : list<List>? = null,
    @SerializedName("city") var city : City? = null
)