package openweather.data.remote.response

import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.utils.io.errors.IOException
import openweather.data.mapper.ErrorMapper
import openweather.domain.models.NetworkResult

abstract class BaseResponse(private val errorMapper: ErrorMapper) {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): NetworkResult<T> {
        return try {
            val response = apiCall()
            NetworkResult.Success(response)
        } catch (exception: ResponseException) {
            NetworkResult.Error(
                errorMapper.mapToModel(exception.response.body<ErrorResponse>())
            )
        } catch (e: IOException) {
            NetworkResult.Error(
                errorMapper.mapToModel(ErrorResponse("500", "Unknown Error"))
            )
        } catch (e: Exception) {
            NetworkResult.Error(
                errorMapper.mapToModel(ErrorResponse("500", "Unknown Error"))
            )
        }
    }
}