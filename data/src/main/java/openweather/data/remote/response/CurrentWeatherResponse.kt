package openweather.data.remote.response

import com.google.gson.annotations.SerializedName
import kotlin.collections.List as list

data class CurrentWeatherResponse(
    @SerializedName("coord") var coord : Coord? = null,
    @SerializedName("weather") var weather : list<Weather>? = null,
    @SerializedName("base") var base : String? = null,
    @SerializedName("main") var main : Main? = null,
    @SerializedName("visibility") var visibility : Int? = null,
    @SerializedName("wind") var wind : Wind? = null,
    @SerializedName("clouds") var clouds : Clouds? = null,
    @SerializedName("rain") var rain : Rain? = null,
    @SerializedName("snow") var snow : Snow? = null,
    @SerializedName("dt") var dt : Int? = null,
    @SerializedName("sys") var sys : Sys? = null,
    @SerializedName("timezone") var timezone : Int? = null,
    @SerializedName("id") var id : Int? = null,
    @SerializedName("name") var name : String? = null,
    @SerializedName("cod") var cod : Int? = null
)