package com.onedev.mygamescompose.core.di

import com.onedev.mygamescompose.core.data.source.remote.RemoteDataSource
import com.onedev.mygamescompose.core.data.source.remote.network.ApiService
import com.onedev.mygamescompose.core.domain.repository.MainRepository
import com.onedev.mygamescompose.core.domain.repository.MainRepositoryImpl
import com.onedev.mygamescompose.core.domain.usecase.MainInteractor
import com.onedev.mygamescompose.core.domain.usecase.MainUseCase
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
    fun provideRemoteDataSource(apiService: ApiService) : RemoteDataSource {
        return RemoteDataSource(apiService)
    }

    @Singleton
    @Provides
    fun provideMainRepository(remoteDataSource: RemoteDataSource) : MainRepository {
        return MainRepositoryImpl(remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideMainInteractor(mainRepository: MainRepository): MainUseCase {
        return MainInteractor(mainRepository)
    }
}