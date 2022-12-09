package com.onedev.mygamescompose.core.data.source.remote

import com.onedev.mygamescompose.core.data.source.remote.network.ApiService
import com.onedev.mygamescompose.core.domain.model.Users
import com.onedev.mygamescompose.core.data.source.remote.network.ApiResponse
import com.onedev.mygamescompose.utils.Mapper.mapToUsers
import com.onedev.mygamescompose.utils.asFlowStateEvent
import kotlinx.coroutines.flow.Flow

class RemoteDataSource(
    private val apiService: ApiService
) {

    suspend fun users(): Flow<ApiResponse<List<Users>>> {
        return apiService.users().asFlowStateEvent {
            it.mapToUsers()
        }
    }
}