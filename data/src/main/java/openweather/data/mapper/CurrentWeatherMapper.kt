package openweather.data.mapper

import openweather.data.remote.response.CurrentWeatherResponse
import openweather.domain.mapper.ResponseMapper
import openweather.domain.models.*

class CurrentWeatherMapper : ResponseMapper<CurrentWeatherResponse, CurrentWeather> {

    override fun mapToModel(entity: CurrentWeatherResponse): CurrentWeather {
        return CurrentWeather(
            Coord(
                entity.coord?.lon,
                entity.coord?.lat
            ),
            entity.weather?.map { weather ->
                Weather(
                    weather.id,
                    weather.main,
                    weather.description,
                    weather.icon
                )
            },
            entity.base,
            Main(
                entity.main?.temp,
                entity.main?.feelsLike,
                entity.main?.tempMin,
                entity.main?.tempMax,
                entity.main?.tempKf,
                entity.main?.pressure,
                entity.main?.humidity,
                entity.main?.seaLevel,
                entity.main?.grndLevel
            ),
            entity.visibility,
            Wind(
                entity.wind?.speed,
                entity.wind?.deg,
                entity.wind?.gust
            ),
            Clouds(
                entity.clouds?.all
            ),
            Rain(
                entity.rain?.one_h,
                entity.rain?.three_h
            ),
            Snow(
                entity.snow?.one_h,
                entity.snow?.three_h
            ),
            entity.dt,
            Sys(
                entity.sys?.type,
                entity.sys?.id,
                entity.sys?.country,
                entity.sys?.sunrise,
                entity.sys?.sunset,
                entity.sys?.pod
            ),
            entity.timezone,
            entity.id,
            entity.name,
            entity.cod
        )
    }

}