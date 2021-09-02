package com.geekbrains.kotlinmvvm.model.repository

import com.example.weatherapp.model.entities.Weather

interface Repository {
    fun getWeatherFromServer(lat: Double, lng: Double): Weather
    fun getWeatherFromLocalStorageRus(): List<Weather>
    fun getWeatherFromLocalStorageWorld(): List<Weather>
    fun getAllHistory(): List<Weather>
    fun saveEntity(weather: Weather)
}