package com.example.weatherapp.model.repository

import com.example.weatherapp.model.entities.Weather
import com.example.weatherapp.model.entities.getRussianCities
import com.example.weatherapp.model.entities.getWorldCities
import com.geekbrains.kotlinmvvm.model.repository.Repository

class RepositoryImpl : Repository {
    override fun getWeatherFromServer() = Weather()
    override fun getWeatherFromLocalStorageRus() = getRussianCities()
    override fun getWeatherFromLocalStorageWorld() = getWorldCities()
}