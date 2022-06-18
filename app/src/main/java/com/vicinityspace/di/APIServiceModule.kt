package com.vicinityspace.di

import com.vicinityspace.data.api.APIService
import com.vicinityspace.data.api.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIServiceModule {

    @Provides
    @Singleton
    fun provideAPIService(): APIService {
        return RetrofitBuilder.apiService
    }
}