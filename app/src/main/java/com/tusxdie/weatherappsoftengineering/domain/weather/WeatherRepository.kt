package com.tusxdie.weatherappsoftengineering.domain.weather

import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeather(lat: Double, lon: Double): Flow<Weather>

    suspend fun getWeatherList(lat: Double, lon: Double): Flow<List<Weather>>
}