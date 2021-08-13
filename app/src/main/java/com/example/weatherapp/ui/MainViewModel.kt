package com.example.weatherapp.ui

import android.util.Log
import androidx.lifecycle.*
import com.example.weatherapp.AppState
import com.example.weatherapp.model.repository.Repository

class MainViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData() = liveDataToObserve

    fun getWeather() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            Thread.sleep(2000)
            liveDataToObserve.postValue(AppState.Success(repository.getWeatherFromLocalStorage()))
        }.start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onViewStart() {
        Log.i("LifecycleEvent", "onStart")
    }
}