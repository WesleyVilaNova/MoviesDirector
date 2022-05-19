package com.example.moviesdirector.view.ui.repository

import com.example.moviesdirector.view.ui.interfaces.WebService
import com.example.moviesdirector.view.ui.utils.Constants

class MainRepository(private val retrofit: WebService) {

    fun getListMovies() = retrofit.getMoviesNetwork(
        Constants.API_KEY,
        Constants.PAGE,
        Constants.LANGUAGE
    )

    fun getListMoviesDetails(id: Int) = retrofit.getMoviesDetails(
        id,
        Constants.API_KEY,
        Constants.LANGUAGE
    )
}
