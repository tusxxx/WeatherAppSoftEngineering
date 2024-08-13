package com.tusxdie.weatherappsoftengineering.ui.forecast

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tusxdie.weatherappsoftengineering.ui.base.LCE
import com.tusxdie.weatherappsoftengineering.ui.components.shimmerLoading
import com.tusxdie.weatherappsoftengineering.ui.theme.MediumPadding
import com.tusxdie.weatherappsoftengineering.ui.theme.SmallPadding
import com.tusxdie.weatherappsoftengineering.ui.theme.WeatherAppSoftEngineeringTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ForecastScreen(viewModel: ForecastViewModel = hiltViewModel<ForecastViewModel>()) {
    val state by viewModel.state.collectAsState()

    ForecastScreen(state)
}

@Composable
private fun ForecastScreen(state: ForecastState) {
    Column(Modifier.fillMaxSize()) {
        LazyColumn(Modifier.padding(MediumPadding).shimmerLoading(state.lce == LCE.Loading)) {
            item {
                Row {
                    TableCell(text = "Дата", weight = 0.5f)
                    TableCell(text = "Температура", weight = 0.5f)
                }
            }
            items(state.forecast) {
                Row {
                    TableCell(text = it.date.formatDate(), weight = 0.5f)
                    TableCell(text = it.temperature.toString(), weight = 0.5f)
                }
            }
        }
    }
}

fun Date.formatDate(): String {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return dateFormat.format(this)
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, MaterialTheme.colorScheme.primary)
            .weight(weight)
            .padding(SmallPadding)
    )
}

@Preview
@Composable
private fun ForecastScreenPreview() {
    WeatherAppSoftEngineeringTheme {
        ForecastScreen(state = ForecastState.mock)
    }
}