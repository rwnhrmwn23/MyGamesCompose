package com.onedev.mygamescompose.core.repository

import com.onedev.mygamescompose.core.model.Users
import com.onedev.mygamescompose.core.network.ApiService
import com.onedev.mygamescompose.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GameRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun users(): Flow<UIState<Users>> = flow {
        emit(UIState.Loading)
        try {
            val response = apiService.users()
            emit(UIState.Success(response))
        } catch (e: Exception) {
            emit(UIState.Error(e))
        }
    }
}