package com.geekbrains.kotlinmvvm.model.repository

import com.example.weatherapp.model.entities.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorageRus(): List<Weather>
    fun getWeatherFromLocalStorageWorld(): List<Weather>
}