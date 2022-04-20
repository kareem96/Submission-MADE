package com.kareemdev.tmdbapp.core.data.source.remote.network

import com.kareemdev.tmdbapp.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie")

    suspend fun getListMovie(
        @Query("api_key") apiKey: String,
    ): ListMovieResponse
}