package com.onedev.mygamescompose.utils

import com.onedev.mygamescompose.core.data.source.remote.network.ApiResponse
import com.onedev.mygamescompose.utils.ErrorUtils.getErrorThrowableMsg
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

typealias FlowState<T> = Flow<ApiResponse<T>>

fun <T, U> Response<T>.asFlowStateEvent(mapper: (T) -> U): FlowState<U> {
    return flow {
        val response = try {
            val body = body()
            if (isSuccessful && body != null) {
                val dataMapper = mapper.invoke(body)
                ApiResponse.Success(dataMapper)
            } else {
                val exception = Exception()
                ApiResponse.Error(exception.stackTraceToString())
            }
        } catch (e: Throwable) {
            ApiResponse.Error(getErrorThrowableMsg(e))
        }
        emit(response)
    }
}