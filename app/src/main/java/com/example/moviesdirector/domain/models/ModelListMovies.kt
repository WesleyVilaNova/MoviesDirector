package com.example.moviesdirector.domain.models

import com.example.moviesdirector.domain.utils.Constants
import java.io.Serializable

data class ModelListMovies(
    val adult: Boolean?,
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
        return getPostImagem()
    }

    private fun getPostImagem(): String {
        return Constants.IMG.plus(poster_path)
    }
}
