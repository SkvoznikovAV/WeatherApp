package com.example.weatherapp.model.repository

import com.example.weatherapp.model.entities.City
import com.example.weatherapp.model.entities.Weather
import com.geekbrains.kotlinmvvm.model.repository.Repository

class RepositoryImpl : Repository {
    override fun getWeatherFromServer() = Weather()
    override fun getWeatherFromLocalStorageRus() = City.getRussianCities()
    override fun getWeatherFromLocalStorageWorld() = City.getWorldCities()
}