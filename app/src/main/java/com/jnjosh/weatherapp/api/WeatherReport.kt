package com.jnjosh.weatherapp.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherReport(
    val id: String? = null,
    val name: String? = null,
    @SerialName("dt") val dateSinceEpoch: Long,
    @SerialName("main") val weatherDetails: WeatherDetails,
    @SerialName("weather") val conditions: List<WeatherCondition>
    )

@Serializable
data class WeatherCondition(
    val icon: String
)

@Serializable
data class WeatherDetails(
    @SerialName("temp") val temperature: Double,
    @SerialName("feels_like") val feelsLike: Double
)

@Serializable
data class WeatherForecast(
    @SerialName("list") val forecasts: List<WeatherReport>
)