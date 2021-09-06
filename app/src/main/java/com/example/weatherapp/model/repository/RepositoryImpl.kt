package com.example.weatherapp.model.repository

import com.example.weatherapp.model.WeatherLoader
import com.example.weatherapp.model.WeatherRepo
import com.example.weatherapp.model.database.Database
import com.example.weatherapp.model.database.HistoryEntity
import com.example.weatherapp.model.entities.City
import com.example.weatherapp.model.entities.Weather
import com.geekbrains.kotlinmvvm.model.repository.Repository

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(lat: Double, lng: Double): Weather {
        val dto = WeatherRepo.api.getWeather(lat, lng).execute().body()

        return Weather(
            temperature = dto?.fact?.temp ?: 0,
            feelsLike = dto?.fact?.feels_like ?: 0,
            condition = dto?.fact?.condition,
            loaded_success = dto != null
        )
    }
    override fun getWeatherFromLocalStorageRus() = City.getRussianCities()
    override fun getWeatherFromLocalStorageWorld() = City.getWorldCities()
    override fun getAllHistory(): List<Weather> = convertHistoryEntityToWeather(Database.db.historyDao().all())

    override fun saveEntity(weather: Weather) {
        Database.db.historyDao().insert(convertWeatherToEntity(weather))
    }

    private fun convertHistoryEntityToWeather(entityList: List<HistoryEntity>): List<Weather> =
        entityList.map {
            Weather(City(it.city, 0.0, 0.0), it.temperature, 0, it.condition)
        }


    private fun convertWeatherToEntity(weather: Weather): HistoryEntity =
        HistoryEntity(0, weather.city.city,
            weather.temperature ?: 0,
            weather.condition ?: ""
        )
}