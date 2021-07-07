package com.jnjosh.weatherapp.ui.view

import androidx.compose.foundation.background
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun WeatherReportHeaderView(text: String) {
    Text(
        text = text,
        modifier = Modifier.background(Color(200, 200, 200))
    )
}
