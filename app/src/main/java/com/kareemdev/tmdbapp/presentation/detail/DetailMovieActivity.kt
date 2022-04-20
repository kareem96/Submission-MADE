package com.kareemdev.tmdbapp.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.kareemdev.tmdbapp.R
import com.kareemdev.tmdbapp.core.domain.model.Movie
import com.kareemdev.tmdbapp.databinding.ActivityDetailMovieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private val detailMovieViewModel: DetailViewModel by viewModel()

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {

            binding.content.tvDetailDescription.text = detailMovie.overview
            binding.content.tvRatingCount.text = detailMovie.createVoteCountToString()
            binding.content.tvPopularity.text = detailMovie.popularity.toString()
            binding.content.tvReleaseDate.text = detailMovie.releaseDate
            supportActionBar?.title = detailMovie.title
            Glide.with(this@DetailMovieActivity)

                .load(getString(R.string.baseUrlImage, detailMovie.posterPath))
                .into(binding.ivDetailImage)

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if(statusFavorite){
            binding.fab.setImageDrawable(

                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        }else{
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )

        }
    }
}