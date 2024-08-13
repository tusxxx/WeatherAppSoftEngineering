package com.tusxdie.weatherappsoftengineering.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.tusxdie.weatherappsoftengineering.data.database.entities.WeatherDb
import java.util.Date

@Dao
interface WeatherDao {
    @Query("SELECT * FROM WeatherDb")
    suspend fun getAll(): List<WeatherDb>

    @Query("DELETE FROM WeatherDb")
    suspend fun deleteAll()

    @Upsert
    suspend fun insertAll(vararg weather: WeatherDb)
}