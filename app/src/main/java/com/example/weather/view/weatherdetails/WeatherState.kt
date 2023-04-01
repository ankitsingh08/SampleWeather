package com.example.weather.view.weatherdetails

import com.example.weather.domain.model.CurrentWeatherDomainResponse

data class WeatherState(
    val name: String = "--",
    val currentTemperature: String = "--",
    val feelsLike: String = "--",
    val minTemp: String = "--",
    val maxTemp: String = "--",
    val weatherIcon: String = "--",
    val weatherDetails: Map<String, String> = emptyMap()
)

fun CurrentWeatherDomainResponse.toPresentation(): WeatherState {
    return WeatherState(
        name = name,
        currentTemperature = main.temp.toInt().toString(),
        feelsLike = main.feels_like.toInt().toString(),
        minTemp = main.temp_min.toInt().toString(),
        maxTemp = main.temp_max.toInt().toString(),
        weatherIcon = weather[0].icon,
        weatherDetails = mapOf(
            "Visibility (km)" to main.temp_min.toString(),
            "Humidity (%)" to main.humidity.toString(),
            "Wind (km/h)" to main.feels_like.toString(),
            "Country" to sys.country,
        )
    )
}
