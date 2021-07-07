package com.jnjosh.weatherapp.ui.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jnjosh.weatherapp.api.WeatherReport
import com.jnjosh.weatherapp.api.WeatherService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherViewModel() : ViewModel() {
    companion object {
        const val tag = "WEATHER-VM"
        const val apiKey = "<enter key>"
    }

    private val weatherCellModel = MutableLiveData<WeatherCellModel>()
    val weatherLiveData: LiveData<WeatherCellModel>
        get() = weatherCellModel

    private val forecastCellModels = MutableLiveData<List<WeatherCellModel>>()
    val forecastLiveData: LiveData<List<WeatherCellModel>>
        get() = forecastCellModels

    private var compositeDisposable = CompositeDisposable()

    fun fetchWeather(latitude: String, longitude: String) {
        compositeDisposable.add(
            WeatherService.buildService()
                .getWeather(latitude, longitude, apiKey)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response -> handleReportResult(response) },
                    { error -> handleError(error) }
                )
        )

        compositeDisposable.add(
            WeatherService.buildService()
                .getForecast(latitude, longitude, apiKey)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map { it.forecasts }
                .subscribe(
                    { response -> handleForecastResults(response) },
                    { error -> handleError(error) }
                )
        )
    }

    private fun handleForecastResults(reports: List<WeatherReport>) {
        forecastCellModels.value = reports.map { WeatherCellModel(it) }
    }

    private fun handleReportResult(report: WeatherReport) {
        weatherCellModel.value = WeatherCellModel(report)
    }

    private fun handleError(error: Throwable) {
        val message = error.localizedMessage
        if (message != null) {
            Log.e(tag, message)
        } else {
            Log.e(tag, "the worst has happened")
        }
    }
}