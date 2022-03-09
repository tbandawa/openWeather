package openweather.data.mapper

import openweather.data.remote.response.ErrorResponse
import openweather.domain.mapper.ResponseMapper
import openweather.domain.models.Error

class ErrorResponseMapper : ResponseMapper<ErrorResponse, Error> {

    override fun mapToModel(entity: ErrorResponse): Error {
        return Error(entity.cod, entity.message)
    }

}