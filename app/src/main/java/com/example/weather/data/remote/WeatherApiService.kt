package com.example.weather.data.remote

import com.example.weather.data.model.CurrentWeatherDetails
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    suspend fun getWeather(
        @Query("lat") latitude : Double,
        @Query("lon") longitude : Double,
    ) : CurrentWeatherDetails

}