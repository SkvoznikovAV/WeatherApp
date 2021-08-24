package com.example.weatherapp.ui.viewModels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.AppState
import com.geekbrains.kotlinmvvm.model.repository.Repository

class DetailsViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun loadData(lat: Double, lng: Double) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            val data = repository.getWeatherFromServer(lat, lng)
            liveDataToObserve.postValue(AppState.Success(listOf(data)))
        }.start()
    }
}