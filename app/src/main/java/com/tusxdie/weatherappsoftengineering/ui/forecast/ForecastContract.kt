package com.tusxdie.weatherappsoftengineering.ui.forecast

import com.tusxdie.weatherappsoftengineering.domain.weather.Weather
import com.tusxdie.weatherappsoftengineering.ui.base.LCE
import com.tusxdie.weatherappsoftengineering.ui.base.MVIIntent
import com.tusxdie.weatherappsoftengineering.ui.base.MVIState
import java.util.Date

sealed interface ForecastIntent : MVIIntent

data class ForecastState(
    val lce: LCE = LCE.Loading,
    val forecast: List<Weather> = listOf(),
) : MVIState {

    companion object {
        val mock = ForecastState(
            lce = LCE.Loaded,
            forecast = listOf(
                Weather(
                    cityName = "Moscow",
                    lat = 0.0,
                    lon = 0.0,
                    temperature = 0.0,
                    windSpeed = 0.0,
                    iconUrl = "",
                    date = Date()
                ),
                Weather(
                    cityName = "Moscow",
                    lat = 0.0,
                    lon = 0.0,
                    temperature = 12.0,
                    windSpeed = 0.0,
                    iconUrl = "",
                    date = Date()
                )
            )
        )
    }
}