package com.jnjosh.weatherapp.ui.model

import com.jnjosh.weatherapp.api.WeatherReport
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class WeatherCellModel(weatherReport: WeatherReport) {
    val temperature: Double = weatherReport.weatherDetails.temperature
    val feelsLike: Double = weatherReport.weatherDetails.feelsLike

    private val date: Date = Date(weatherReport.dateSinceEpoch * 1000)
    val formattedDate: String
        get() {
            val formatter = SimpleDateFormat("MMM dd", Locale.getDefault())
            return formatter.format(date)
        }

    val hourMarker: String
        get() {
            val formatter = SimpleDateFormat("HH", Locale.getDefault())
            return formatter.format(date)
        }
}


