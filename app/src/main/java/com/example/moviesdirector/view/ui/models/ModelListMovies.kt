package com.example.moviesdirector.view.ui.models

import com.example.moviesdirector.view.ui.utils.Constants
import java.io.Serializable

data class ModelListMovies(
    val adult: Boolean,
    val backdrop_path: String,
    val overview: String,
    val popularity: String?,
    val release_date: String?,
    val poster_path: String,
    val title: String,
    val vote_average: Float?,
    val id: Int
) : Serializable {

    fun getPostImg(): String {
        return Constants.IMG + poster_path
    }
}
