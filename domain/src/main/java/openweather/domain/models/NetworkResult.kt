package openweather.domain.models

sealed class NetworkResult<M>(
    val data: M? = null,
    val message: String? = null
) {
    class Success<M>(data: M) : NetworkResult<M>(data)
    class Error<M>(message: String, data: M? = null) : NetworkResult<M>(data, message)
    class Loading<M> : NetworkResult<M>()
}