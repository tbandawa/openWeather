package openweather.data.remote.response

import com.google.gson.annotations.SerializedName
import kotlin.collections.List as list

data class OneCallResponse (
    @SerializedName("lat") var lat : Double? = null,
    @SerializedName("lon") var lon : Double? = null,
    @SerializedName("timezone") var timezone : String? = null,
    @SerializedName("timezone_offset") var timezoneOffset : Int? = null,
    @SerializedName("current") var current : Current? = null,
    @SerializedName("minutely") var minutely : list<Minutely>? = null,
    @SerializedName("hourly") var hourly : list<Hourly>? = null,
    @SerializedName("daily") var daily : list<Daily>? = null
)