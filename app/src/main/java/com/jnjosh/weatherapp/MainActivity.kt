package com.jnjosh.weatherapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jnjosh.weatherapp.ui.theme.WeatherAppTheme
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    weatherReport(getWeather())
                }
            }
        }
    }

    private fun getWeather(): Observable<WeatherReport> {
        return WeatherService.buildService()
            .getWeather("35.9940", "-78.8986", "b09de02719afe3c487b3f19fcebe72bb")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}

@Composable
fun weatherReport(weatherObservable: Observable<WeatherReport>) {

    val initialReport = WeatherReport("", "", 0, WeatherDetails(0.0, 0.0), listOf(WeatherCondition("")))
    val report: WeatherReport by weatherObservable.subscribeAsState(initial = initialReport)

    Text(
        text = "Weather report from: ${report.name}, it's ${report.weatherDetails.temperature}ºC!",
        fontSize = 30.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.width(500.dp)
    )
}
