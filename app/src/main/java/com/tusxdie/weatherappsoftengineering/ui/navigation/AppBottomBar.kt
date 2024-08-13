package com.tusxdie.weatherappsoftengineering.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

private const val EMPTY_ROUTE = ""

@Composable
fun AppBottomBar(navController: NavController) {
    val bottomScreens = remember {
        listOf(
            Screen.Main,
            Screen.Forecast,
        )
    }
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestinationRoute = currentBackStackEntry?.destination?.route ?: EMPTY_ROUTE
    val isBottomBarVisible = bottomScreens.any {
        currentDestinationRoute.contains(it.toRoute())
    }

    AnimatedVisibility(
        visible = isBottomBarVisible,
        enter = slideInVertically { it },
        exit = slideOutVertically { it }
    ) {
        NavigationBar {
            bottomScreens.forEach { screen ->
                val isSelected = currentDestinationRoute.contains(screen.toRoute())

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) {
                            navController.navigate(screen) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.iconId),
                            contentDescription = null
                        )
                    },
                    label = { Text(text = stringResource(id = screen.titleId)) }
                )
            }
        }
    }
}
