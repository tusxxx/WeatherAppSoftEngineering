package com.tusxdie.weatherappsoftengineering.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tusxdie.weatherappsoftengineering.data.database.daos.WeatherDao
import com.tusxdie.weatherappsoftengineering.data.database.entities.WeatherDb

@Database(entities = [WeatherDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): WeatherDao
}