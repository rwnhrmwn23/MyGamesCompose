package com.onedev.mygamescompose.core.domain.repository

import com.onedev.mygamescompose.core.data.source.remote.RemoteDataSource
import com.onedev.mygamescompose.core.data.source.remote.network.ApiResponse
import com.onedev.mygamescompose.core.data.source.remote.network.NetworkResource
import com.onedev.mygamescompose.core.data.source.remote.network.StateEvent
import com.onedev.mygamescompose.core.domain.model.GameDetails
import com.onedev.mygamescompose.core.domain.model.Games
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor (
    private val remoteDataSource: RemoteDataSource
) : MainRepository {
    override fun games(pageSize: Int): Flow<StateEvent<List<Games>>> =
        object : NetworkResource<List<Games>>() {
            override suspend fun createCall(): Flow<ApiResponse<List<Games>>> {
                return remoteDataSource.users(pageSize)
            }
        }.asFlow()

    override fun detailsGame(id: Int): Flow<StateEvent<GameDetails>> =
        object : NetworkResource<GameDetails>() {
            override suspend fun createCall(): Flow<ApiResponse<GameDetails>> {
                return remoteDataSource.detailsGame(id)
            }
        }.asFlow()
}