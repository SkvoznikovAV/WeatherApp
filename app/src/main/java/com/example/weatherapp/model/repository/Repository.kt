package com.example.weatherapp.model.repository

import com.example.weatherapp.model.entities.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage(): Weather
}