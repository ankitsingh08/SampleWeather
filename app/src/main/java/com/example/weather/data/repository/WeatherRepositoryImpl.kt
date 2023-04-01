package com.example.weather.data.repository

import com.example.weather.data.model.LocationData
import com.example.weather.data.model.NetworkResult
import com.example.weather.data.model.toDomainResponse
import com.example.weather.data.remote.WeatherApiService
import com.example.weather.domain.model.CurrentWeatherDomainResponse
import com.example.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val weatherApiService: WeatherApiService) :
    WeatherRepository {

    override fun getWeather(locationData: LocationData): Flow<NetworkResult<CurrentWeatherDomainResponse>> {
        return flow {
            try {
                val response = weatherApiService.getWeather(
                    locationData.latitude,
                    locationData.longitude
                )
                emit(NetworkResult.Success(response.toDomainResponse()))
            } catch (exception: Exception) {
                emit(NetworkResult.Failure(exception))
            }
        }
    }
}