package com.onedev.mygamescompose.core.network

import com.onedev.mygamescompose.core.model.Users
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun users(): Users
}