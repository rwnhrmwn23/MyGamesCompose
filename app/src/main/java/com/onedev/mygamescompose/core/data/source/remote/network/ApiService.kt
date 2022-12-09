package com.onedev.mygamescompose.core.data.source.remote.network

import com.onedev.mygamescompose.core.data.source.remote.response.UsersResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun users(): Response<UsersResponse>
}