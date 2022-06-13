package com.example.moviesdirector.view.ui.data.repository

import com.example.moviesdirector.view.ui.data.api.WebService
import com.example.moviesdirector.view.ui.domain.utils.Constants

class MainRepository(private val retrofit: WebService) {

    fun getListMovies() = retrofit.getListMoviesNetwork(
        Constants.API_KEY,
        Constants.PAGE,
        Constants.LANGUAGE
    )

    fun getListMoviesDetails(id: Int) = retrofit.getDetailsMoviesNetWork(
        id,
        Constants.API_KEY,
        Constants.LANGUAGE
    )
}
