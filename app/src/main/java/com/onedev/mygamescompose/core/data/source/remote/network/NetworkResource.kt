package com.onedev.mygamescompose.core.data.source.remote.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

abstract class NetworkResource<ResultType> {

    private var result: Flow<StateEvent<ResultType>> = flow {
        emit(StateEvent.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emit(StateEvent.Success(apiResponse.data))
            }
            is ApiResponse.Error -> {
                onFetchFailed()
                emit(StateEvent.Error(apiResponse.errorMessage))
            }
            is ApiResponse.Empty -> {
                onFetchFailed()
                emit(StateEvent.Error("Empty Data"))
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract suspend fun createCall(): Flow<ApiResponse<ResultType>>

    fun asFlow(): Flow<StateEvent<ResultType>> = result
}