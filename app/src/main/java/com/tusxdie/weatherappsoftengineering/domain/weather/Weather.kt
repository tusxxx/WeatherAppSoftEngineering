package com.tusxdie.weatherappsoftengineering.domain.weather

import java.util.Date

data class Weather(
    val cityName: String,
    val lat: Double,
    val lon: Double,
    val temperature: Double,
    val windSpeed: Double,
    val iconUrl: String,
    val date: Date
)