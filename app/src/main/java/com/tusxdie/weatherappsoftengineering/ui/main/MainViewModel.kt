package com.tusxdie.weatherappsoftengineering.ui.main

import androidx.lifecycle.viewModelScope
import com.tusxdie.weatherappsoftengineering.domain.usecases.GetLastLatLonUseCase
import com.tusxdie.weatherappsoftengineering.domain.utils.PermissionChecker
import com.tusxdie.weatherappsoftengineering.domain.weather.WeatherRepository
import com.tusxdie.weatherappsoftengineering.ui.base.LCE
import com.tusxdie.weatherappsoftengineering.ui.base.MVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val permissionChecker: PermissionChecker,
    private val weatherRepository: WeatherRepository,
    private val getLastLatLonUseCase: GetLastLatLonUseCase
) : MVIViewModel<MainState, MainIntent>(MainState()) {
    init {
        checkForLocationPermission()
        fetchWeather()
    }

    private fun fetchWeather() {
        viewModelScope.launch {
            if (!permissionChecker.isLocationPermissionGranted()) return@launch
            val currentLatLon = getLastLatLonUseCase().firstOrNull() ?: return@launch
            weatherRepository.getWeather(currentLatLon.first, currentLatLon.second).collect { weather ->
                _state.update {
                    it.copy(
                        lce = LCE.Loaded,
                        temperature = weather.temperature.toFloat(),
                        city = weather.cityName
                    )
                }
            }
        }
    }

    private fun checkForLocationPermission() {
        _state.update {
            it.copy(
                isPermissionGranted = permissionChecker.isLocationPermissionGranted()
            )
        }
    }

    override fun onIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.PermissionGranted -> _state.update {
                fetchWeather()
                it.copy(isPermissionGranted = intent.isGranted)
            }
        }
    }
}