package com.tusxdie.weatherappsoftengineering.data.utils

import android.content.Context
import com.tusxdie.weatherappsoftengineering.domain.utils.PermissionChecker
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PermissionCheckerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PermissionChecker {
    override fun isLocationPermissionGranted(): Boolean {
        return context.checkSelfPermission(
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }
}