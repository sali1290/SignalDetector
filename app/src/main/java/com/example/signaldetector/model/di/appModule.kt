package com.example.signaldetector.model.di

import com.example.signaldetector.model.utility.NetWorkHelper
import com.example.signaldetector.viewmodel.LocationByCellInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { NetWorkHelper(get()) }
    viewModel { LocationByCellInfoViewModel() }
}