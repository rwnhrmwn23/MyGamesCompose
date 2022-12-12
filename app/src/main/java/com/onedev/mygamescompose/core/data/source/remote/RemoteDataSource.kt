package com.onedev.mygamescompose.core.data.source.remote

import com.onedev.mygamescompose.core.data.source.remote.network.ApiResponse
import com.onedev.mygamescompose.core.data.source.remote.network.ApiService
import com.onedev.mygamescompose.core.domain.model.GameDetails
import com.onedev.mygamescompose.core.domain.model.Games
import com.onedev.mygamescompose.utils.Mapper.mapToDetailGames
import com.onedev.mygamescompose.utils.Mapper.mapToGames
import com.onedev.mygamescompose.utils.asFlowStateEvent
import kotlinx.coroutines.flow.Flow

class RemoteDataSource(
    private val apiService: ApiService
) {

    suspend fun users(pageSize: Int): Flow<ApiResponse<List<Games>>> {
        return apiService.games(pageSize).asFlowStateEvent {
            it.mapToGames()
        }
    }

    suspend fun detailsGame(id: Int): Flow<ApiResponse<GameDetails>> {
        return apiService.detailsGame(id).asFlowStateEvent {
            it.mapToDetailGames()
        }
    }
}