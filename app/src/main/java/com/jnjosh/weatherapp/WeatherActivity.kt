package com.jnjosh.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import com.jnjosh.weatherapp.ui.theme.WeatherAppTheme
import com.jnjosh.weatherapp.ui.model.WeatherViewModel
import com.jnjosh.weatherapp.ui.view.WeatherReportView

class WeatherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val model: WeatherViewModel by viewModels()
        model.fetchWeather("35.9940", "-78.8986")

        setContent {
            WeatherAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    WeatherReportView(
                        model = model
                    )
                }
            }
        }
    }
}
