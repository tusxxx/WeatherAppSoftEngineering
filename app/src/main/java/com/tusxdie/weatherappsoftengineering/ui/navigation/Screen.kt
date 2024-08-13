package com.tusxdie.weatherappsoftengineering.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.tusxdie.weatherappsoftengineering.R
import kotlinx.serialization.Serializable

// Handmade navigation graph
sealed interface Screen {
    @Serializable
    data object Main : BottomBarScreen(R.string.main, R.drawable.ic_home)
    @Serializable
    data object Forecast : BottomBarScreen(R.string.forecast, R.drawable.ic_forecast)
}

@Serializable
sealed class BottomBarScreen(@StringRes val titleId: Int, @DrawableRes val iconId: Int) : Screen

fun Screen.toRoute() = this::class.qualifiedName.orEmpty()
