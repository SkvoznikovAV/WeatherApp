package com.example.weatherapp

import com.example.weatherapp.model.entities.Weather

sealed class AppState {
    data class Success(val weatherData: List<Weather>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object NotLoaded : AppState()
    object Loading : AppState()
}