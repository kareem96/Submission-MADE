package com.kareemdev.tmdbapp.core.utils

import com.kareemdev.tmdbapp.core.data.source.local.entity.MovieEntity
import com.kareemdev.tmdbapp.core.data.source.remote.response.MovieResponse
import com.kareemdev.tmdbapp.core.domain.model.Movie


object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                isFavorite = false,
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> = input.map {
        Movie(
            movieId = it.movieId,
            title = it.title,
            overview = it.overview,
            releaseDate = it.releaseDate,
            posterPath = it.posterPath,
            voteCount = it.voteCount,
            voteAverage = it.voteAverage,
            popularity = it.popularity,
            isFavorite = it.isFavorite,
        )
    }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        title = input.title,
        overview = input.overview,
        posterPath = input.posterPath,
        popularity = input.popularity,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount,
        releaseDate = input.releaseDate,
        isFavorite = input.isFavorite,
    )

}