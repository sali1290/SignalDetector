package com.example.signaldetector.model.di

import com.example.signaldetector.model.api.UnwiredApiService
import com.example.signaldetector.model.repo.UnwiredRepo
import com.example.signaldetector.model.repo.UnwiredRepoImpl
import com.example.signaldetector.viewmodel.LocationByCellInfoViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    // Get location by cell info di
    single {
        Retrofit.Builder()
            .baseUrl("https://ap1.unwiredlabs.com/")
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(UnwiredApiService::class.java)
    }
    single<UnwiredRepo> { UnwiredRepoImpl(get()) }
    viewModel { LocationByCellInfoViewModel(get()) }
}