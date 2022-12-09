package com.onedev.mygamescompose.core.domain.repository

import com.onedev.mygamescompose.core.data.source.remote.RemoteDataSource
import com.onedev.mygamescompose.core.data.source.remote.network.ApiResponse
import com.onedev.mygamescompose.core.data.source.remote.network.NetworkResource
import com.onedev.mygamescompose.core.data.source.remote.network.StateEvent
import com.onedev.mygamescompose.core.domain.model.Users
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor (
    private val remoteDataSource: RemoteDataSource
) : MainRepository {
    override fun users(): Flow<StateEvent<List<Users>>> =
        object : NetworkResource<List<Users>>() {
            override suspend fun createCall(): Flow<ApiResponse<List<Users>>> {
                return remoteDataSource.users()
            }
        }.asFlow()
}