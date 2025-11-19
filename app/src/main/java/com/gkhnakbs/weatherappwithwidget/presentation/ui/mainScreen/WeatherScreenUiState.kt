package com.gkhnakbs.weatherappwithwidget.presentation.ui.mainScreen

import com.gkhnakbs.weatherappwithwidget.domain.model.data.WeatherResponse

sealed class WeatherUiState{
    data object Loading : WeatherUiState()
    data class Success(val weatherData : WeatherResponse) : WeatherUiState()
    data object Idle : WeatherUiState()
    data class Error(val message : String) : WeatherUiState()

}