package com.tusxdie.weatherappsoftengineering.domain.utils

interface PermissionChecker {
    fun isLocationPermissionGranted(): Boolean
}