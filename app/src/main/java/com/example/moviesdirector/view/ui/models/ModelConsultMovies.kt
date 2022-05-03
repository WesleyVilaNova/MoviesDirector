package com.example.moviesdirector.view.ui.models

import com.google.gson.annotations.SerializedName

data class ModelConsultMovies(
    val adult: Boolean,
    val backdrop_path: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val title: String,
    val vote_average: Double
) {

    fun getDetailsMovies(): String {
        return overview
    }

    fun getPostImg(): String {
        return "https://image.tmdb.org/t/p/w450$poster_path"
    }

    fun getPostImgDetails(): String {
        return "https://image.tmdb.org/t/p/w450$backdrop_path"
    }

}

