package com.kareemdev.tmdbapp.core.data

import com.kareemdev.tmdbapp.core.data.source.local.LocalDataSource
import com.kareemdev.tmdbapp.core.data.source.remote.RemoteDataSource
import com.kareemdev.tmdbapp.core.data.source.remote.network.ApiResponse
import com.kareemdev.tmdbapp.core.data.source.remote.response.MovieResponse
import com.kareemdev.tmdbapp.core.domain.model.Movie
import com.kareemdev.tmdbapp.core.domain.repository.IMovieRepository
import com.kareemdev.tmdbapp.core.utils.AppExecutors
import com.kareemdev.tmdbapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
): IMovieRepository{
    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(){
            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getAllMovie()
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map { DataMapper.mapEntitiesToDomain(it) }
            }

        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute{localDataSource.setFavoriteMovie(movieEntity, state)}
    }

}