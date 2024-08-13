package com.tusxdie.weatherappsoftengineering.di

import com.tusxdie.weatherappsoftengineering.data.utils.PermissionCheckerImpl
import com.tusxdie.weatherappsoftengineering.domain.utils.PermissionChecker
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilsModule {
    @Binds
    @Singleton
    abstract fun bindPermissionChecker(impl: PermissionCheckerImpl): PermissionChecker
}