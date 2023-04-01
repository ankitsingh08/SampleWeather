package com.example.weather.view.weatherdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.utils.mapToDrawableResource

@Composable
fun CurrentTempView(weatherState: WeatherState) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier
            .wrapContentHeight(),
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = 24.dp,
                    start = 24.dp,
                    end = 16.dp
                )
                .wrapContentHeight()
        ) {
            Row(modifier = Modifier.wrapContentHeight()) {
                Column(
                    modifier = Modifier
                        .weight(.65f)
                        .align(Alignment.CenterVertically),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = weatherState.currentTemperature.toInt().toString() + "\u00B0",
                        fontSize = 84.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.size(4.dp))
                    Text(
                        text = weatherState.name,
                        fontSize = 32.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.size(8.dp))
                    Text(
                        text = stringResource(id = R.string.feels_like, weatherState.feelsLike),
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.size(3.dp))
                    Text(
                        text = stringResource(id = R.string.min_max_temp, weatherState.minTemp, weatherState.maxTemp),
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(Modifier.size(8.dp))
            Image(
                modifier = Modifier
                    .weight(.35f)
                    .padding(top = 16.dp)
                    .wrapContentHeight(),
                painter = painterResource((weatherState.weatherIcon).mapToDrawableResource()),
                contentDescription = ""
            )
            }
        }
    }
}