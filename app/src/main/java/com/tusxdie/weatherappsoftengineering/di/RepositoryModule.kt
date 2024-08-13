package com.tusxdie.weatherappsoftengineering.di

import com.tusxdie.weatherappsoftengineering.data.repositories.weather.WeatherRepositoryImpl
import com.tusxdie.weatherappsoftengineering.domain.weather.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository
}