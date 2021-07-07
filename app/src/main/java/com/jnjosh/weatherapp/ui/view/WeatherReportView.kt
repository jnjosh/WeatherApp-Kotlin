package com.jnjosh.weatherapp.ui.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import com.jnjosh.weatherapp.ui.model.WeatherCellModel
import com.jnjosh.weatherapp.ui.model.WeatherViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherReportView(model: WeatherViewModel) {
    val report: WeatherCellModel? by model.weatherLiveData.observeAsState(null)
    val forecast: List<WeatherCellModel>? by model.forecastLiveData.observeAsState(null)

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Show current Weather Report
        stickyHeader { WeatherReportHeaderView(text = "Now") }
        item {
            report?.let {
                WeatherReportCellView(it)
                Divider()
            }
        }

        // Show forecasted weather Reports
        stickyHeader { WeatherReportHeaderView(text = "Forecast") }
        if (forecast != null) {
            items(forecast!!) { model ->
                WeatherReportCellView(model)
                Divider()
            }
        }
    }
}