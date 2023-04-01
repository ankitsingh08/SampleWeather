package com.example.weather.view.weatherdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weather.data.model.LocationData
import com.example.weather.data.model.NetworkResult
import com.example.weather.domain.model.CloudsDomainModel
import com.example.weather.domain.model.CoordDomainModel
import com.example.weather.domain.model.CurrentWeatherDomainResponse
import com.example.weather.domain.model.MainDomainModel
import com.example.weather.domain.model.SysDomainModel
import com.example.weather.domain.model.WeatherDomainModel
import com.example.weather.domain.model.WindDomainModel
import com.example.weather.domain.usecase.GetWeatherDetailsUseCase
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.EOFException

class WeatherDetailsViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getWeatherDetailsUseCase: GetWeatherDetailsUseCase

    private lateinit var viewModel: WeatherDetailsViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockitoAnnotations.openMocks(this)
        viewModel = WeatherDetailsViewModel(getWeatherDetailsUseCase)
    }

    @Test
    fun `get weather details success scenario`() = runBlocking {
        val testData = getMockWeatherResponse()

        whenever(getWeatherDetailsUseCase(LocationData(100.06, 15.88)))
            .thenReturn(flowOf(NetworkResult.Success(testData)))

        val expectedState = viewModel.getCurrentWeather(LocationData(100.06, 15.88))

        viewModel.getCurrentWeather(LocationData(100.06, 15.88))

        val job = launch {
            viewModel.weatherResult.collect {
                assertEquals(it , expectedState)
            }
        }
        job.cancel()
    }


    @Test
    fun `get weather details error scenario`() = runBlocking {
        val exception = EOFException()

        whenever(getWeatherDetailsUseCase(LocationData(100.06, 15.88))).thenReturn(flowOf(NetworkResult.Failure(exception)))

        val expectedError = viewModel.getCurrentWeather(LocationData(100.06, 15.88))

        val job = launch {
            viewModel.weatherResult.collect {
                assertEquals(it , expectedError)
            }
        }
        job.cancel()
    }

    private fun getMockWeatherResponse(): CurrentWeatherDomainResponse {
        val weather = WeatherDomainModel("clear sky","01d", 800, "Clear")
        val weatherList = listOf(weather)

        return CurrentWeatherDomainResponse(
            "stations",
            CloudsDomainModel(90),
            200,
            CoordDomainModel(-0.13, 51.51),
            1485789600,
            2643743,
            MainDomainModel(280.32, 1012.00, 81.00, 250.15, 281.15, 271.15),
            "London",
            SysDomainModel("GB", 5091, 0.0103, 1485762037, 1485794875, 1),
            -25200,
            weatherList,
            WindDomainModel(350.00, 1.5)
        )
    }
}