package com.kareemdev.tmdbapp.presentation.detail

import androidx.lifecycle.ViewModel
import com.kareemdev.tmdbapp.core.domain.model.Movie
import com.kareemdev.tmdbapp.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    fun setFavoriteMovie(movie:Movie, newStatus:Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}