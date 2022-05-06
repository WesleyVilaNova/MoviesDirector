package com.example.moviesdirector.view.ui.models

import com.example.moviesdirector.view.ui.utils.Constants
import java.io.Serializable

data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val overview: String,
    val popularity: String?,
    val release_date: String?,
    val poster_path: String,
    val title: String,
    val vote_average: String?,
    val id: Double
) : Serializable {

    fun getDetailsMovies(): String {
        return Constants.BASE_URL + Constants.TYPE + "$id" + Constants.API_KEY + Constants.LANGUAGE
        //"https://api.themoviedb.org/3/movie/id?api_key=319f821ad80072b8c1dd98a08e31346c&page=1&language=pt-BR"
    }

    fun getPostImg(): String {
        return Constants.IMG + poster_path
    }

    fun getPostImgDetails(): String {
        return Constants.IMG + backdrop_path

    }

}

