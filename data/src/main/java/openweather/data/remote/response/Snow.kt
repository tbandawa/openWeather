package openweather.data.remote.response

import com.google.gson.annotations.SerializedName

data class Snow(
    @SerializedName("1h") var one_h : Double,
    @SerializedName("3h") var three_h : Double
)
