package com.example.weatherapp.ui.viewModels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.AppState
import com.example.weatherapp.model.entities.City
import com.geekbrains.kotlinmvvm.model.repository.Repository

class DetailsViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun loadData(lat: Double, lng: Double, city: City) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            val data = repository.getWeatherFromServer(lat, lng)
            if (data.loaded_success) {
                data.city = City(city.city,lat,lng)
                liveDataToObserve.postValue(AppState.Success(listOf(data)))

                repository.saveEntity(data)
            } else {
                liveDataToObserve.postValue(AppState.NotLoaded)
            }

        }.start()
    }
}