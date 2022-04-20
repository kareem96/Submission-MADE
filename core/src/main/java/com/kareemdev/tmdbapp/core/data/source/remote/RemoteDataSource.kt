package com.kareemdev.tmdbapp.core.data.source.remote

import android.util.Log
import com.kareemdev.tmdbapp.core.data.source.remote.network.ApiResponse
import com.kareemdev.tmdbapp.core.data.source.remote.network.ApiService
import com.kareemdev.tmdbapp.core.data.source.remote.response.MovieResponse
import com.kareemdev.tmdbapp.core.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource constructor(private val apiService: ApiService){

    /*private val apiKey = "0be47f8a233f2718d99d0c366369f1f8"*/

    suspend fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>>{
        return flow {
            try {
                val response = apiService.getListMovie(Constants.API_KEY)
                val dataArray = response.results
                if(dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e( "getAllMovie: ", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}