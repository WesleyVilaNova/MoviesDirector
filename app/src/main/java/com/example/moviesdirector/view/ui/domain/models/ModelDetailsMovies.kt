package com.example.moviesdirector.view.ui.domain.models

import com.example.moviesdirector.view.ui.domain.utils.Constants

data class ModelDetailsMovies(
    val overview: String?,
    val backdrop_path: String?,
    val homepage: String?,
    val popularity: String?,
    val genres: List<ModelDetailsListGenres>,
    val title: String?,
    val tagline: String?,
    val release_date: String?,
    val vote_average: String?,
) {

    fun getPostImgDetails(): String {
        return Constants.IMG + backdrop_path
    }

    fun getUrlDetailsMovie(): String {
        return homepage.toString()
    }
}
