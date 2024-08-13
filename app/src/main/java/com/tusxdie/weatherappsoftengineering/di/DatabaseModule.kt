package com.tusxdie.weatherappsoftengineering.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tusxdie.weatherappsoftengineering.App
import com.tusxdie.weatherappsoftengineering.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "weather_db"
    ).build()

    @Provides
    @Singleton
    fun provideWeatherDao(appDatabase: AppDatabase) = appDatabase.userDao()
}