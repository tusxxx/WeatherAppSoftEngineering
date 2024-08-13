package com.tusxdie.weatherappsoftengineering.domain.usecases

import android.Manifest
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.core.content.getSystemService
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/*
 * Need to migrate to google play services
 */
@ActivityRetainedScoped
class GetLastLatLonUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke(): Flow<Pair<Double, Double>> = flow {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return@flow
        }
        val locationManager = context.getSystemService<LocationManager>()
        val lastKnownLocation = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            ?: locationManager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        if (lastKnownLocation != null)
            emit(lastKnownLocation.latitude to lastKnownLocation.longitude)
        else
            return@flow
    }
}
