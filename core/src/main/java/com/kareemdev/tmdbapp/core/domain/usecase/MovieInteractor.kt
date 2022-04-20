package com.kareemdev.tmdbapp.core.domain.usecase

import com.kareemdev.tmdbapp.core.data.Resource
import com.kareemdev.tmdbapp.core.domain.model.Movie
import com.kareemdev.tmdbapp.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor (private val movieRepository: IMovieRepository): MovieUseCase{
    override fun getAllMovie(): Flow<Resource<List<Movie>>> {
        return movieRepository.getAllMovie()
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return movieRepository.getFavoriteMovie()
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        return movieRepository.setFavoriteMovie(movie, state)
    }
}