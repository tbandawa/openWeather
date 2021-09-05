package openweather.data.remote.response

import com.google.gson.annotations.SerializedName
import kotlin.collections.List as list

data class List (
    @SerializedName("dt") var dt : Int? = null,
    @SerializedName("main") var main : Main? = null,
    @SerializedName("weather") var weather : list<Weather>? = null,
    @SerializedName("clouds") var clouds : Clouds? = null,
    @SerializedName("wind") var wind : Wind? = null,
    @SerializedName("visibility") var visibility : Int? = null,
    @SerializedName("pop") var pop : Double? = null,
    @SerializedName("rain") var rain : Rain? = null,
    @SerializedName("snow") var snow : Snow? = null,
    @SerializedName("sys") var sys : Sys? = null,
    @SerializedName("dt_txt") var dtTxt : String? = null
)