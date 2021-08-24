package com.example.weatherapp.model.repository

import com.example.weatherapp.model.WeatherLoader
import com.example.weatherapp.model.entities.City
import com.example.weatherapp.model.entities.Weather
import com.geekbrains.kotlinmvvm.model.repository.Repository

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(lat: Double, lng: Double): Weather {
        val dto = WeatherLoader.loadWeather(lat, lng)
        return Weather(
            temperature = dto?.fact?.temp ?: 0,
            feelsLike = dto?.fact?.feels_like ?: 0,
            condition = dto?.fact?.condition
        )
    }
    override fun getWeatherFromLocalStorageRus() = City.getRussianCities()
    override fun getWeatherFromLocalStorageWorld() = City.getWorldCities()
}