package openweather.domain.models

import openweather.domain.models.Error as ErrorModel

sealed class NetworkResult<out M> {
    class Success<out M>(val data: M) : NetworkResult<M>()
    class Error(val data: ErrorModel? = null) : NetworkResult<Nothing>()
    class Failure(val message: String) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
    object Empty : NetworkResult<Nothing>()
}
