package com.example.signaldetector.model.di

import com.example.signaldetector.model.repo.SIMCardRepo
import com.example.signaldetector.model.repoimpl.SIMCardRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
fun interface RepoModule {

    @Binds
    @Singleton
    fun bindSIMCardRepo(simCardRepoImpl: SIMCardRepoImpl): SIMCardRepo

}