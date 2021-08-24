package openweather.data.remote.response

import com.google.gson.annotations.SerializedName

data class Clouds (
    @SerializedName("all") var all : Int
)