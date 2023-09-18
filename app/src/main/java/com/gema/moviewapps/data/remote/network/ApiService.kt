package com.gema.moviewapps.data.remote.network

import com.gema.moviewapps.data.remote.response.MoviewResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @GET("discover/movie")
    suspend fun dataMovie(
        @Header("Authorization") token: String
    ): MoviewResponse
}