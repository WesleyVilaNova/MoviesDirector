package com.example.moviesdirector.domain.models

import com.example.moviesdirector.domain.utils.Constants

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
        return getPostImg()
    }

    fun getUrlDetailsMovie(): String {
        return getUrl()
    }

    private fun getUrl(): String {
        return homepage.toString()
    }

    private fun getPostImg(): String {
        return Constants.IMG + backdrop_path
    }
}
