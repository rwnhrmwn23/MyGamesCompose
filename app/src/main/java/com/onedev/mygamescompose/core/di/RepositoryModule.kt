package com.onedev.mygamescompose.core.di

import com.onedev.mygamescompose.core.network.ApiService
import com.onedev.mygamescompose.core.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideGameRepository(apiService: ApiService) : GameRepository {
        return GameRepository(apiService)
    }
}