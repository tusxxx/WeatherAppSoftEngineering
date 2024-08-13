package com.tusxdie.weatherappsoftengineering.ui.main

import com.tusxdie.weatherappsoftengineering.ui.base.MVIIntent
import com.tusxdie.weatherappsoftengineering.ui.base.LCE
import com.tusxdie.weatherappsoftengineering.ui.base.MVIState

sealed interface MainIntent : MVIIntent {
    data class PermissionGranted(val isGranted: Boolean) : MainIntent
}

data class MainState(
    val lce: LCE = LCE.Loading,
    val city: String = "",
    val temperature: Float = 0f,
    val isPermissionGranted: Boolean = false
) : MVIState {

    companion object {
        val mock = MainState(
            lce = LCE.Loading,
            city = "Moscow",
            temperature = 10.21f,
            isPermissionGranted = false
        )
    }
}
