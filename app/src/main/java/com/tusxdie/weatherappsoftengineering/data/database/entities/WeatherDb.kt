package com.tusxdie.weatherappsoftengineering.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherDb(
    @PrimaryKey
    val date: Long,
    val cityName: String,
    val lat: Double,
    val lon: Double,
    val temperature: Double,
    val windSpeed: Double,
    val iconUrl: String
)