package com.example.moviesdirector.view.ui.views.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesdirector.R
import com.example.moviesdirector.databinding.ActivityDetailsMoviesBinding
import com.example.moviesdirector.view.ui.models.Result
import com.squareup.picasso.Picasso

class DetailsMoviesActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailsMoviesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recuperando()

    }

    private fun recuperando() {
        val movie: Result? = intent.getSerializableExtra("key") as Result?

        if (movie != null) {
            binding.textViewDetailsMovies.text = movie.overview
            binding.titleMovies.text = movie.title
            Picasso.get().load(movie.getPostImgDetails()).into(binding.imageViewBackdropPath)
        }

    }
}