package openweather.data.remote.response

import com.google.gson.annotations.SerializedName
import kotlin.collections.List as list

data class List (
    @SerializedName("dt") var dt : Int,
    @SerializedName("main") var main : Main,
    @SerializedName("weather") var weather : list<Weather>,
    @SerializedName("clouds") var clouds : Clouds,
    @SerializedName("wind") var wind : Wind,
    @SerializedName("visibility") var visibility : Int,
    @SerializedName("pop") var pop : Double,
    @SerializedName("rain") var rain : Rain,
    @SerializedName("sys") var sys : Sys,
    @SerializedName("dt_txt") var dtTxt : String
)