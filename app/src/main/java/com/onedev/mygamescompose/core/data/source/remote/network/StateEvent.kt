package com.onedev.mygamescompose.core.data.source.remote.network

sealed class StateEvent<T> {
    class Loading<T>: StateEvent<T>()
    data class Error<T>(val exception: String): StateEvent<T>()
    data class Success<T>(val data: T): StateEvent<T>()
}