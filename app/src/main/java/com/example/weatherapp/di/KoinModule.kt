package com.example.weatherapp.di

import com.example.weatherapp.model.repository.RepositoryImpl
import com.example.weatherapp.ui.viewModels.MainViewModel
import com.geekbrains.kotlinmvvm.model.repository.Repository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }

    //View models
    viewModel { MainViewModel(get()) }
}