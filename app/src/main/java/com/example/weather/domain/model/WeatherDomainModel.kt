package com.example.weather.domain.model

data class WeatherDomainModel(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)