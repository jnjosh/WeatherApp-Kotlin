package com.jnjosh.weatherapp.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapServiceClient {

    @GET("/data/2.5/weather")
    fun getWeather(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "imperial"): Observable<WeatherReport>

    @GET("/data/2.5/forecast")
    fun getForecast(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "imperial"): Observable<WeatherForecast>

}