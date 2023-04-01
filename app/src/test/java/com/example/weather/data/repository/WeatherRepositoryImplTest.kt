package com.example.weather.data.repository

import com.example.weather.data.model.Clouds
import com.example.weather.data.model.Coord
import com.example.weather.data.model.CurrentWeatherDetails
import com.example.weather.data.model.LocationData
import com.example.weather.data.model.Main
import com.example.weather.data.model.NetworkResult
import com.example.weather.data.model.Sys
import com.example.weather.data.model.Weather
import com.example.weather.data.model.Wind
import com.example.weather.data.model.toDomainResponse
import com.example.weather.data.remote.WeatherApiService
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.RuntimeException

class WeatherRepositoryImplTest {

    @Mock
    private lateinit var weatherApiService: WeatherApiService

    private lateinit var weatherRepositoryImpl: WeatherRepositoryImpl


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        weatherRepositoryImpl = WeatherRepositoryImpl(weatherApiService)
    }

    @Test
    fun `get weather data returns success scenario`()  = runBlocking {
        val testData = getMockWeatherResponse()
        val expectedOutput = NetworkResult.Success(testData.toDomainResponse())
        whenever(weatherApiService.getWeather(100.08, 12.05)).thenReturn(testData)

        val flow = weatherRepositoryImpl.getWeather(LocationData(100.08, 12.05))

        // Verify
        flow.collect { data->
            assertEquals(expectedOutput, data)
        }
    }

    @Test
    fun `get weather details returns error`()  = runBlocking {

        val exception = RuntimeException()
        val expectedOutput: NetworkResult.Failure<RuntimeException>  = NetworkResult.Failure(exception)
        whenever(weatherApiService.getWeather(100.008, 12.05)).thenThrow(exception)

        val flow = weatherRepositoryImpl.getWeather(LocationData(100.008, 12.05))

        // Verify
        flow.collect{ data ->
            assertEquals(expectedOutput, data)
        }
    }

    private fun getMockWeatherResponse(): CurrentWeatherDetails {
        val weather = Weather("clear sky","01d", 800, "Clear")
        val weatherList = listOf(weather)

        return CurrentWeatherDetails(
            "stations",
            Clouds(90),
            200,
            Coord(-0.13, 51.51),
            1485789600,
            2643743,
            Main(280.32, 1012.00, 81.00, 250.15, 281.15, 271.15),
            "London",
            Sys("GB", 5091, 0.0103, 1485762037, 1485794875, 1),
            -25200,
            weatherList,
            Wind(350.00, 1.5)
        )
    }



}