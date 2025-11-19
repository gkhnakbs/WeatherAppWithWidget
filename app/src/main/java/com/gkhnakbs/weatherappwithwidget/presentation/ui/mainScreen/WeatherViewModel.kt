package com.gkhnakbs.weatherappwithwidget.presentation.ui.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkhnakbs.weatherappwithwidget.domain.repository.WeatherRepository
import com.gkhnakbs.weatherappwithwidget.domain.repository.WeatherRepositoryForWidget
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Gökhan Akbaş on 19/11/2025.
 */

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    companion object {
        private const val USER_LATITUDE = 39.9334
        private const val USER_LONGITUDE = 32.8597
    }


    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Idle)
    val uiState = _uiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000L),
        false
    )


    init {
        fetchWeather()
    }

    fun fetchWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.update {
                    WeatherUiState.Loading
                }
                val result = repository.getWeatherData(USER_LATITUDE, USER_LONGITUDE)
                result?.let {
                    _uiState.update {
                        WeatherUiState.Success(result)
                    }
                } ?: run {
                    _uiState.update {
                        WeatherUiState.Error("No data found")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.update {
                    WeatherUiState.Error(e.message.toString())
                }
            }

        }
    }

}