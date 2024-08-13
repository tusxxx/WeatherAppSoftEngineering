package com.tusxdie.weatherappsoftengineering.ui.forecast

import androidx.lifecycle.viewModelScope
import com.tusxdie.weatherappsoftengineering.domain.usecases.GetLastLatLonUseCase
import com.tusxdie.weatherappsoftengineering.domain.weather.WeatherRepository
import com.tusxdie.weatherappsoftengineering.ui.base.LCE
import com.tusxdie.weatherappsoftengineering.ui.base.MVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val getLastLatLonUseCase: GetLastLatLonUseCase
) : MVIViewModel<ForecastState, ForecastIntent>(ForecastState()) {
    init {
        fetchWeather()
    }

    private fun fetchWeather() {
        viewModelScope.launch {
            val currentLatLon = getLastLatLonUseCase().firstOrNull() ?: return@launch
            weatherRepository.getWeatherList(currentLatLon.first, currentLatLon.second).collect { weather ->
                _state.update {
                    it.copy(
                        lce = LCE.Loaded,
                        forecast = weather
                    )
                }
            }
        }
    }
    override fun onIntent(intent: ForecastIntent) {

    }
}
