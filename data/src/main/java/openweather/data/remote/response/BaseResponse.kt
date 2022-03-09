package openweather.data.remote.response

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import okio.IOException
import openweather.domain.mapper.ResponseMapper
import openweather.domain.models.NetworkResult
import retrofit2.Response
import openweather.domain.models.Error as ErrorModel

abstract class BaseResponse {

    suspend fun <R, T> safeApiCall(
        responseMapper: ResponseMapper<R, T>,
        apiCall: suspend () -> Response<R>
    ): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(responseMapper.mapToModel(body))
                }
            }
            val errorResponse = convertErrorBody(response)
            if (errorResponse != null) {
                return NetworkResult.Error(ErrorModel(errorResponse.cod, errorResponse.message))
            }
            return NetworkResult.Failure("Unknown Response")
        } catch (e: Exception) {
            return NetworkResult.Failure(e.message ?: e.toString())
        }
    }

    private fun <R> convertErrorBody(response: Response<R>): ErrorResponse? {
        val parser = JsonParser()
        val mJson: JsonElement?
        return try {
            mJson = parser.parse(response.errorBody()?.string())
            val gson = Gson()
            gson.fromJson(mJson, ErrorResponse::class.java)
        } catch (ex: IOException) {
            null
        }
    }

}