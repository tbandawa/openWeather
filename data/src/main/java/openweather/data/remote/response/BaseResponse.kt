package openweather.data.remote.response

import openweather.domain.mapper.ResponseMapper
import openweather.domain.models.NetworkResult
import retrofit2.Response

abstract class BaseResponse {

    suspend fun <R, T> safeApiCall(
        mapper: ResponseMapper<R, T>,
        apiCall: suspend () -> Response<R>
    ): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(mapper.mapToModel(body))
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Error! $errorMessage")

}