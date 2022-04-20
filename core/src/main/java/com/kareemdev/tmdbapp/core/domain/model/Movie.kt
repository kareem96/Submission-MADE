package com.kareemdev.tmdbapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie(
    val movieId:Int,
    val title:String,
    val overview:String,
    val posterPath:String,
    val popularity:Double,
    val releaseDate:String,
    val voteAverage:Double,
    val voteCount:Int,
    val isFavorite:Boolean,

):Parcelable{
    fun createVoteCountToString(): String =
        "$voteAverage from $voteCount reviews"
}
