package com.kareemdev.tmdbapp.di

import com.kareemdev.tmdbapp.core.domain.usecase.MovieInteractor
import com.kareemdev.tmdbapp.core.domain.usecase.MovieUseCase
import com.kareemdev.tmdbapp.presentation.detail.DetailViewModel

import com.kareemdev.tmdbapp.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> {MovieInteractor(get())}
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get())}
}