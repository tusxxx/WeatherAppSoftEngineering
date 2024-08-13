package com.tusxdie.weatherappsoftengineering.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tusxdie.weatherappsoftengineering.ui.forecast.ForecastScreen
import com.tusxdie.weatherappsoftengineering.ui.main.MainScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { AppBottomBar(navController) },
        content = {
            NavHost(
                navController = navController,
                startDestination = Screen.Main,
                modifier = Modifier.padding(it)
            ) {
                composable<Screen.Main> { MainScreen() }
                composable<Screen.Forecast> { ForecastScreen() }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
