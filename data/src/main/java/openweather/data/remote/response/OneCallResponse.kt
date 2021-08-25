package openweather.data.remote.response

import com.google.gson.annotations.SerializedName
import kotlin.collections.List as list

data class OneCallResponse (
    @SerializedName("lat") var lat : Double,
    @SerializedName("lon") var lon : Double,
    @SerializedName("timezone") var timezone : String,
    @SerializedName("timezone_offset") var timezoneOffset : Int,
    @SerializedName("current") var current : Current,
    @SerializedName("minutely") var minutely : list<Minutely>,
    @SerializedName("hourly") var hourly : list<Hourly>,
    @SerializedName("daily") var daily : list<Daily>
)