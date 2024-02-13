package openweather.data.mapper

import openweather.data.remote.response.FiveDayWeatherForecastResponse
import openweather.domain.mapper.ResponseMapper
import openweather.domain.models.*
import openweather.domain.models.List

class FiveDayWeatherMapper : ResponseMapper<FiveDayWeatherForecastResponse, FiveDayWeatherForecast> {

    override fun mapToModel(entity: FiveDayWeatherForecastResponse): FiveDayWeatherForecast {
        return FiveDayWeatherForecast(
            entity.cod,
            entity.message,
            entity.cnt,
            entity.list?.map { list ->
                List(
                    list.dt,
                    Main(
                        list.main?.temp,
                        list.main?.feelsLike,
                        list.main?.tempMin,
                        list.main?.tempMax,
                        list.main?.tempKf,
                        list.main?.pressure,
                        list.main?.humidity,
                        list.main?.seaLevel,
                        list.main?.grndLevel
                    ),
                    list.weather?.map { weather ->
                        Weather(
                            weather.id,
                            weather.main,
                            weather.description,
                            weather.icon
                        )
                    },
                    Clouds(
                        list.clouds?.all
                    ),
                    Wind(
                        list.wind?.speed,
                        list.wind?.deg,
                        list.wind?.gust
                    ),
                    list.visibility,
                    list.pop,
                    Rain(
                        list.rain?.one_h,
                        list.rain?.three_h
                    ),
                    Sys(
                        list.sys?.type,
                        list.sys?.id,
                        list.sys?.country,
                        list.sys?.sunrise,
                        list.sys?.sunset,
                        list.sys?.pod
                    ),
                    list.dtTxt
                )
            },
            City(
                entity.city?.id,
                entity.city?.name,
                Coord(
                    entity.city?.coord?.lon,
                    entity.city?.coord?.lat
                ),
                entity.city?.country,
                entity.city?.population,
                entity.city?.timezone,
                entity.city?.sunrise,
                entity.city?.sunset
            )
        )
    }

}