package com.jnjosh.weatherapp.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import kotlinx.serialization.json.*
import okhttp3.MediaType.Companion.toMediaType

object WeatherService {
    private val client = OkHttpClient
        .Builder()
        .build()

    fun buildService(): OpenWeatherMapServiceClient {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            prettyPrint = true
        }

        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(client)
            .build()
            .create(OpenWeatherMapServiceClient::class.java)
    }
}