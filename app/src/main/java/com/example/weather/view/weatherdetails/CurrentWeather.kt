package com.example.weather.view.weatherdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CurrentWeather(weatherState: WeatherState) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CurrentTempView(weatherState)
        GridViewForExtraWeatherDetails(weatherState)
    }

}

@Preview(showBackground = true)
@Composable
fun CurrentWeatherPreview() {
    CurrentWeather(
        WeatherState(
            name = "London",
            currentTemperature = "12.0",
            feelsLike = "10.0",
            minTemp = "8.0",
            maxTemp = "13.0",
            weatherIcon = "_01d",
            weatherDetails = mapOf(
                "Temp Min (ºC)" to "5",
                "Temp Max (ºC)" to "15",
                "Feels Like (ºC)" to "10",
                "Humidity (%)" to "77"
            )
        )
    )
}