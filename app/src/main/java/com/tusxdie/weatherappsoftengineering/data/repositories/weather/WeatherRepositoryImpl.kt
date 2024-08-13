package com.tusxdie.weatherappsoftengineering.data.repositories.weather

import android.util.Log
import com.tusxdie.weatherappsoftengineering.data.database.daos.WeatherDao
import com.tusxdie.weatherappsoftengineering.data.database.entities.WeatherDb
import com.tusxdie.weatherappsoftengineering.data.network.OpenWeatherAPI
import com.tusxdie.weatherappsoftengineering.domain.weather.Weather
import com.tusxdie.weatherappsoftengineering.domain.weather.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: OpenWeatherAPI,
    private val weatherDao: WeatherDao
) : WeatherRepository {
    override suspend fun getWeather(lat: Double, lon: Double): Flow<Weather> = flow {
        runCatching {
            val response = weatherApi.getWeather(lat, lon)
            response
                .toDomain()
                .also {
                    weatherDao.insertAll(it.toEntity())
                }
        }.onSuccess {
            emit(it)
        }.onFailure {
            weatherDao.getAll()
                .firstOrNull()
                ?.toDomain()
                ?.also { emit(it) }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getWeatherList(lat: Double, lon: Double): Flow<List<Weather>> =
        flow {
            runCatching {
                val response = weatherApi.getWeatherList(lat, lon)
                response
                    .toDomain()
                    .distinctBy { it.date.date }
                    .drop(1) // First element is the current weather
                    .also { weatherList ->
                        val entities = weatherList.map { it.toEntity() }
                        weatherDao.insertAll(*entities.toTypedArray())
                    }
            }.onSuccess {
                emit(it)
            }.onFailure {
                weatherDao.getAll()
                    .map(WeatherDb::toDomain)
                    .also { emit(it) }
            }
        }.flowOn(Dispatchers.IO)
}