package openweather.data.remote.response

import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("1h") var one_h : Double = .0,
    @SerializedName("3h") var three_h : Double = .0
)
