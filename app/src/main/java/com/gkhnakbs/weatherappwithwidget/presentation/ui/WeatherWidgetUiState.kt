package com.gkhnakbs.weatherappwithwidget.presentation.ui

/**
 * Created by Gökhan Akbaş on 27/10/2025.
 */

data class WeatherWidgetUiState(
    val isLoading: Boolean,
    val temperature: String,
    val humidity: String,
    val location: String,
    val apparentTemperature: String,
    val lastUpdate: String
)