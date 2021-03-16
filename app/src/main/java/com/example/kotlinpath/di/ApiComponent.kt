package com.example.kotlinpath.di

import com.example.kotlinpath.model.CountriesService
import com.example.kotlinpath.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: CountriesService)

    fun inject(viewModel: ListViewModel)
}