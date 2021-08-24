package openweather.data.remote.response

import com.google.gson.annotations.SerializedName

data class OneCallResponse (
    @SerializedName("lat") var lat : Double,
    @SerializedName("lon") var lon : Double,
    @SerializedName("timezone") var timezone : String,
    @SerializedName("timezone_offset") var timezoneOffset : Int,
    @SerializedName("current") var current : Current,
    @SerializedName("minutely") var minutely : List<Minutely>,
    @SerializedName("hourly") var hourly : List<Hourly>,
    @SerializedName("daily") var daily : List<Daily>
)