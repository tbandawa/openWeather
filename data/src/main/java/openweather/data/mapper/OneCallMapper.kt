package openweather.data.mapper

import openweather.data.remote.response.OneCallResponse
import openweather.domain.mapper.ResponseMapper
import openweather.domain.models.*

class OneCallMapper : ResponseMapper<OneCallResponse, OneCall> {

    override fun mapToModel(response: OneCallResponse): OneCall {
        return OneCall(
            response.lat,
            response.lon,
            response.timezone,
            response.timezoneOffset,
            Current(
                response.current?.dt,
                response.current?.sunrise,
                response.current?.sunset,
                response.current?.temp,
                response.current?.feelsLike,
                response.current?.pressure,
                response.current?.humidity,
                response.current?.dewPoint,
                response.current?.uvi,
                response.current?.clouds,
                response.current?.visibility,
                response.current?.windSpeed,
                response.current?.windDeg,
                response.current?.windGust,
                response.current?.weather?.map { weather ->
                    Weather(
                        weather.id,
                        weather.main,
                        weather.description,
                        weather.icon
                    )
                },
                Rain(
                    response.current?.rain?.one_h,
                    response.current?.rain?.three_h
                ),
                Snow(
                    response.current?.snow?.one_h,
                    response.current?.snow?.three_h
                )
            ),
            response.minutely?.map { minutely ->
                Minutely(
                    minutely.dt,
                    minutely.precipitation
                )
            },
            response.hourly?.map { hourly ->
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
                    hourly.weather?.map { weather ->
                        Weather(
                            weather.id,
                            weather.main,
                            weather.description,
                            weather.icon
                        )
                    },
                    hourly.pop,
                    Rain(
                        hourly.rain?.one_h,
                        hourly.rain?.three_h
                    )
                )
            },
            response.daily?.map { daily ->
                Daily(
                    daily.dt,
                    daily.sunrise,
                    daily.sunset,
                    daily.moonrise,
                    daily.moonset,
                    daily.moonPhase,
                    Temp(
                        daily.temp?.day,
                        daily.temp?.min,
                        daily.temp?.max,
                        daily.temp?.night,
                        daily.temp?.eve,
                        daily.temp?.morn
                    ),
                    FeelsLike(
                        daily.feelsLike?.day,
                        daily.feelsLike?.night,
                        daily.feelsLike?.eve,
                        daily.feelsLike?.morn
                    ),
                    daily.pressure,
                    daily.humidity,
                    daily.dewPoint,
                    daily.windSpeed,
                    daily.windDeg,
                    daily.windGust,
                    daily.weather?.map { weather ->
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