package com.example.weatherapp.model.repository

import com.example.weatherapp.model.entities.Weather

class RepositoryImpl : Repository {
    override fun getWeatherFromServer() = Weather()

    override fun getWeatherFromLocalStorage() = Weather()
}