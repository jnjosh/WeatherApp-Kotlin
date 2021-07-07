package com.jnjosh.weatherapp.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jnjosh.weatherapp.ui.model.WeatherCellModel

@Composable
fun WeatherReportCellView(model: WeatherCellModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = model.formattedDate,
                fontSize = 9.sp
            )
            Text(
                text = model.hourMarker,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Column(
            modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier.padding(horizontal = 6.dp)
                ) {
                    Text(
                        text = "${model.temperature}ºF",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                    )
                }
                Column {
                    Text(
                        text = "(Feels like it's ${model.feelsLike}ºF)",
                        fontStyle = FontStyle.Italic,
                        fontSize = 12.sp
                    )

                }
            }

        }

    }
}
