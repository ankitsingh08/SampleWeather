package com.example.weather.domain.usecase


import com.example.weather.data.model.LocationData
import com.example.weather.data.model.NetworkResult
import com.example.weather.di.IoDispatcher
import com.example.weather.domain.model.CurrentWeatherDomainResponse
import com.example.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherDetailsUseCase @Inject constructor(
    private val repository: WeatherRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : BaseUseCase<LocationData, CurrentWeatherDomainResponse>(ioDispatcher) {

    override fun execute(locationData: LocationData): Flow<NetworkResult<CurrentWeatherDomainResponse>> {
        return repository.getWeather(locationData)
    }

}