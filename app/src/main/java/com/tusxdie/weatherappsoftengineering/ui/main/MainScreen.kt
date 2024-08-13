package com.tusxdie.weatherappsoftengineering.ui.main

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.tusxdie.weatherappsoftengineering.R
import com.tusxdie.weatherappsoftengineering.ui.base.isLoading
import com.tusxdie.weatherappsoftengineering.ui.components.PaddedCard
import com.tusxdie.weatherappsoftengineering.ui.components.shimmerLoading
import com.tusxdie.weatherappsoftengineering.ui.theme.MediumPadding
import com.tusxdie.weatherappsoftengineering.ui.theme.SmallPadding
import com.tusxdie.weatherappsoftengineering.ui.theme.WeatherAppSoftEngineeringTheme

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    MainScreen(
        state = state,
        onIntent = viewModel::onIntent,
    )
}

@Composable
private fun MainScreen(
    state: MainState,
    onIntent: (MainIntent) -> Unit = {},
) {
    val isLoading = state.lce.isLoading()
    val requestGeoLocationPermission =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            onIntent(MainIntent.PermissionGranted(isGranted))
        }

    Column(
        Modifier
            .fillMaxSize()
            .padding(MediumPadding),
        verticalArrangement = Arrangement.spacedBy(MediumPadding)
    ) {
        PaddedCard(
            Modifier
                .fillMaxWidth()
                .shimmerLoading(enabled = isLoading)
        ) {
            Text(
                text = stringResource(R.string.weather_now),
                style = MaterialTheme.typography.headlineLarge
            )
            Text(text = state.city, style = MaterialTheme.typography.titleLarge)
        }

        PaddedCard(
            Modifier
                .fillMaxWidth()
                .shimmerLoading(enabled = isLoading)
        ) {
            Text(
                text = stringResource(R.string.current_temperature),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(R.string.celcius, state.temperature),
                style = MaterialTheme.typography.headlineMedium
            )
        }
        PaddedCard(
            Modifier
                .fillMaxWidth()
                .shimmerLoading(enabled = isLoading)
                .padding(SmallPadding)
        ) {
            Text(text = temperatureToComment(state.temperature), style = MaterialTheme.typography.titleLarge)
        }
        AnimatedVisibility(
            visible = !state.isPermissionGranted,
            enter = slideInHorizontally { -it },
            exit = slideOutHorizontally { -it }
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(Modifier.weight(1f))
                Text(text = stringResource(R.string.without_permission_not_working), style = MaterialTheme.typography.titleMedium)
                ElevatedButton(
                    onClick = { requestGeoLocationPermission.launch(ACCESS_COARSE_LOCATION) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.give_access_to_location), style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

@Composable
private fun temperatureToComment(temperature: Float): String {
    return when {
        temperature < 0f -> stringResource(R.string.cold_advice)
        temperature in 0f..15f -> stringResource(R.string.normal_advice)
        temperature > 15 -> stringResource(R.string.warm_advice)
        else -> ""
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    val state = MainState.mock

    WeatherAppSoftEngineeringTheme {
        MainScreen(state = state)
    }
}
