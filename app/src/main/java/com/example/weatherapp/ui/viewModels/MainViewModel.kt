package com.example.weatherapp.ui.viewModels

import androidx.lifecycle.*
import com.example.weatherapp.AppState
import com.geekbrains.kotlinmvvm.model.repository.Repository

class MainViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData() = liveDataToObserve
    fun getWeatherFromLocalSourceRus() = getDataFromLocalSource(isRussian = true)
    fun getWeatherFromLocalSourceWorld() = getDataFromLocalSource(isRussian = false)

    private fun getDataFromLocalSource(isRussian: Boolean) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            liveDataToObserve.postValue(
                if(isRussian) {
                    AppState.Success(repository.getWeatherFromLocalStorageRus())
                } else {
                    AppState.Success(repository.getWeatherFromLocalStorageWorld())
                }
            )
        }.start()
    }
}