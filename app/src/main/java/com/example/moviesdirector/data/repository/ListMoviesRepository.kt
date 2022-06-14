package com.example.moviesdirector.data.repository

import com.example.moviesdirector.data.api.WebService
import com.example.moviesdirector.domain.utils.Constants

class ListMoviesRepository(private val retrofit: WebService) {

    fun getListMovies() = retrofit.getListMoviesNetwork(
        Constants.API_KEY,
        Constants.PAGE,
        Constants.LANGUAGE
    )

    fun getDetailsMovies(id: Int) = retrofit.getDetailsMoviesNetWork(
        id,
        Constants.API_KEY,
        Constants.LANGUAGE
    )
}
