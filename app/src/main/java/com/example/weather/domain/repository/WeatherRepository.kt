package com.example.weather.domain.repository

import com.example.weather.data.model.LocationData
import com.example.weather.data.model.NetworkResult
import com.example.weather.domain.model.CurrentWeatherDomainResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun getWeather(locationData: LocationData): Flow<NetworkResult<CurrentWeatherDomainResponse>>
}