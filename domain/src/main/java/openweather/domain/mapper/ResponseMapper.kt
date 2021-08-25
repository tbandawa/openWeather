package openweather.domain.mapper

interface ResponseMapper<I, O>  {

    fun mapToModel(entity: I): O

}