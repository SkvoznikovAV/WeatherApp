package com.example.weatherapp.model.entities

data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = 0
)

fun getDefaultCity() = City("Саратов",  51.5406, 46.0086)