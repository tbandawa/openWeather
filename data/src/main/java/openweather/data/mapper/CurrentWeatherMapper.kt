package openweather.data.mapper

import openweather.data.remote.response.CurrentWeatherResponse
import openweather.domain.mapper.ResponseMapper
import openweather.domain.models.*

class CurrentWeatherMapper : ResponseMapper<CurrentWeatherResponse, CurrentWeather> {

    override fun mapToModel(response: CurrentWeatherResponse): CurrentWeather {
        return CurrentWeather(
            Coord(
                response.coord.lon,
                response.coord.lat
            ),
            response.weather.map { weather ->
                Weather(
                    weather.id,
                    weather.main,
                    weather.description,
                    weather.icon
                )
            },
            response.base,
            Main(
                response.main.temp,
                response.main.feelsLike,
                response.main.tempMin,
                response.main.tempMax,
                response.main.tempKf,
                response.main.pressure,
                response.main.humidity,
                response.main.seaLevel,
                response.main.grndLevel
            ),
            response.visibility,
            Wind(
                response.wind.speed,
                response.wind.deg,
                response.wind.gust
            ),
            Clouds(
                response.clouds?.all
            ),
            Rain(
                response.rain?.one_h,
                response.rain?.three_h
            ),
            Snow(
                response.snow?.one_h,
                response.snow?.three_h
            ),
            response.dt,
            Sys(
                response.sys.type,
                response.sys.id,
                response.sys.country,
                response.sys.sunrise,
                response.sys.sunset,
                response.sys.pod
            ),
            response.timezone,
            response.id,
            response.name,
            response.cod
        )
    }

}