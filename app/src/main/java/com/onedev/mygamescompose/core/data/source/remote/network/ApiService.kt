package com.onedev.mygamescompose.core.data.source.remote.network

import com.onedev.mygamescompose.BuildConfig.API_KEY
import com.onedev.mygamescompose.core.data.source.remote.response.GamesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun games(
        @Query("page_size") pageSize: Int,
        @Query("key") key: String? = API_KEY,
    ): Response<GamesResponse>
}