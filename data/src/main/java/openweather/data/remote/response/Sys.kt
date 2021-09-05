package openweather.data.remote.response

import com.google.gson.annotations.SerializedName

data class Sys (
    @SerializedName("type") var type : Int? = null,
    @SerializedName("id") var id : Int? = null,
    @SerializedName("message") var message : String? = null,
    @SerializedName("country") var country : String? = null,
    @SerializedName("sunrise") var sunrise : Int? = null,
    @SerializedName("sunset") var sunset : Int? = null,
    @SerializedName("pod") var pod : String? = null
)