package com.tusxdie.weatherappsoftengineering.data.network

import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "10f8b7eb9c6d1064bcc05d902599bf1c"
private const val DEFAULT_UNITS = "metric"

interface OpenWeatherAPI {
    @GET("weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = DEFAULT_UNITS,
        @Query("appid") appId: String = API_KEY,
        @Query("lang") lang: String = "ru"
    ): WeatherResponse

    @GET("weather")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("units") units: String = DEFAULT_UNITS,
        @Query("appid") appId: String = API_KEY,
        @Query("lang") lang: String = "ru"
    ): WeatherResponse

    @GET("forecast")
    suspend fun getWeatherList(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = DEFAULT_UNITS,
        @Query("appid") appId: String = API_KEY,
        @Query("lang") lang: String = "ru"
    ): WeatherListResponse
}