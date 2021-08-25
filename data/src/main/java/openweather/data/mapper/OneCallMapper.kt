package openweather.data.mapper

import openweather.data.remote.response.OneCallResponse
import openweather.domain.mapper.ResponseMapper
import openweather.domain.models.*

class OneCallMapper : ResponseMapper<OneCallResponse, OneCall> {

    override fun mapToModel(entity: OneCallResponse): OneCall {
        return OneCall(
            entity.lat,
            entity.lon,
            entity.timezone,
            entity.timezoneOffset,
            Current(
                entity.current.dt,
                entity.current.sunrise,
                entity.current.sunset,
                entity.current.temp,
                entity.current.feelsLike,
                entity.current.pressure,
                entity.current.humidity,
                entity.current.dewPoint,
                entity.current.uvi,
                entity.current.clouds,
                entity.current.visibility,
                entity.current.windSpeed,
                entity.current.windDeg,
                entity.current.windGust,
                entity.current.weather.map { weather ->
                    Weather(
                        weather.id,
                        weather.main,
                        weather.description,
                        weather.icon
                    )
                },
                Rain(
                    entity.current.rain.one_h,
                    entity.current.rain.three_h
                )
            ),
            entity.minutely.map { minutely ->
                Minutely(
                    minutely.dt,
                    minutely.precipitation
                )
            },
            entity.hourly.map { hourly ->
                Hourly(
                    hourly.dt,
                    hourly.temp,
                    hourly.feelsLike,
                    hourly.pressure,
                    hourly.humidity,
                    hourly.dewPoint,
                    hourly.uvi,
                    hourly.clouds,
                    hourly.visibility,
                    hourly.windSpeed,
                    hourly.windDeg,
                    hourly.windGust,
                    hourly.weather.map { weather ->
                        Weather(
                            weather.id,
                            weather.main,
                            weather.description,
                            weather.icon
                        )
                    },
                    hourly.pop,
                    Rain(
                        hourly.rain.one_h,
                        hourly.rain.three_h
                    )
                )
            },
            entity.daily.map { daily ->
                Daily(
                    daily.dt,
                    daily.sunrise,
                    daily.sunset,
                    daily.moonrise,
                    daily.moonset,
                    daily.moonPhase,
                    Temp(
                        daily.temp.day,
                        daily.temp.min,
                        daily.temp.max,
                        daily.temp.night,
                        daily.temp.eve,
                        daily.temp.morn
                    ),
                    FeelsLike(
                        daily.feelsLike.day,
                        daily.feelsLike.night,
                        daily.feelsLike.eve,
                        daily.feelsLike.morn
                    ),
                    daily.pressure,
                    daily.humidity,
                    daily.dewPoint,
                    daily.windSpeed,
                    daily.windDeg,
                    daily.windGust,
                    daily.weather.map { weather ->
                        Weather(
                            weather.id,
                            weather.main,
                            weather.description,
                            weather.icon
                        )
                    },
                    daily.clouds,
                    daily.pop,
                    daily.rain,
                    daily.uvi
                )
            }
        )
    }

}