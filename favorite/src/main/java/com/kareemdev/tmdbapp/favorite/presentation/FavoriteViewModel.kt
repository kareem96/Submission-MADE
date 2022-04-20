package com.kareemdev.tmdbapp.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kareemdev.tmdbapp.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel(){
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}