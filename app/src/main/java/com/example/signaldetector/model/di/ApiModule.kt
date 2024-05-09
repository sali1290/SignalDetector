package com.example.signaldetector.model.di

import com.example.signaldetector.model.services.UnwiredLabsRemoteApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(): UnwiredLabsRemoteApi {
        return Retrofit.Builder()
            .baseUrl(UnwiredLabsRemoteApi.BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(UnwiredLabsRemoteApi::class.java)
    }


}