package com.example.weather.view.weatherdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.model.LocationData
import com.example.weather.data.model.NetworkResult
import com.example.weather.domain.usecase.GetWeatherDetailsUseCase
import com.example.weather.utils.errorHandlerHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(private val getWeatherDetailsUseCase: GetWeatherDetailsUseCase) :
    ViewModel() {

    private val _isInProgress = MutableStateFlow(true)
    val isInProgress: StateFlow<Boolean> = _isInProgress

    private val _weatherResult = MutableStateFlow(WeatherState())
    val weatherResult: StateFlow<WeatherState> = _weatherResult

    private val _isShowErrorDialog = MutableStateFlow(Pair(false, -1))
    val isShowErrorDialog: StateFlow<Pair<Boolean, Int>> = _isShowErrorDialog

    fun resetErrorDialog() {
        _isShowErrorDialog.value = Pair(false, -1)
    }

    fun getCurrentWeather(locationData: LocationData) {
        viewModelScope.launch {
            _isInProgress.value = true
            getWeatherDetailsUseCase(locationData).collectLatest { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        _isInProgress.value = false
                        _weatherResult.value = response.data.toPresentation()
                    }
                    is NetworkResult.Failure -> {
                        val networkError = errorHandlerHelper(response.throwable)
                        _isShowErrorDialog.value = Pair(true, networkError.errorMessage)
                    }
                }
            }
        }
    }

}