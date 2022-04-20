package com.kareemdev.tmdbapp.core.domain.usecase

import com.kareemdev.tmdbapp.core.data.Resource
import com.kareemdev.tmdbapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}