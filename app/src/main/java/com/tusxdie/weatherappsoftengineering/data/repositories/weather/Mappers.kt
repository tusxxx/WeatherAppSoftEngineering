package com.tusxdie.weatherappsoftengineering.data.repositories.weather

import com.tusxdie.weatherappsoftengineering.data.database.entities.WeatherDb
import com.tusxdie.weatherappsoftengineering.data.network.WeatherListResponse
import com.tusxdie.weatherappsoftengineering.data.network.WeatherResponse
import com.tusxdie.weatherappsoftengineering.domain.weather.Weather
import java.util.Date

fun WeatherResponse.toDomain() = Weather(
    cityName = name,
    lat = coord.lat,
    lon = coord.lon,
    temperature = main.temp,
    windSpeed = wind.speed,
    iconUrl = weather[0].icon,
    date = Date(dt.toLong() * 1000)
)

fun WeatherListResponse.toDomain() = this.list.map {
    Weather(
        cityName = this.city.name,
        lat = this.city.coord.lat,
        lon = this.city.coord.lon,
        temperature = it.main.temp,
        windSpeed = it.wind.speed,
        iconUrl = it.weather[0].icon,
        date = Date(it.dt.toLong() * 1000)
    )
}

fun Weather.toEntity() = WeatherDb(
    cityName = cityName,
    lat = lat,
    lon = lon,
    temperature = temperature,
    windSpeed = windSpeed,
    iconUrl = iconUrl,
    date = date.time
)

fun WeatherDb.toDomain() = Weather(
    cityName = cityName,
    lat = lat,
    lon = lon,
    temperature = temperature,
    windSpeed = windSpeed,
    iconUrl = iconUrl,
    date = Date(date)
)