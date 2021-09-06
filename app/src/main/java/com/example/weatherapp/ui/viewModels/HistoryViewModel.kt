package com.example.weatherapp.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.AppState
import com.geekbrains.kotlinmvvm.model.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: Repository) : ViewModel(),
    CoroutineScope by MainScope() {
    val historyLiveData: MutableLiveData<AppState> = MutableLiveData()

    fun getAllHistory() {
        historyLiveData.value = AppState.Loading
        launch(Dispatchers.IO) {
            historyLiveData.postValue(AppState.Success(repository.getAllHistory()))
        }
    }
}