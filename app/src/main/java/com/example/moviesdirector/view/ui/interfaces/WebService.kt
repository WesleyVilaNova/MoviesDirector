package com.example.moviesdirector.view.ui.interfaces

import com.example.moviesdirector.view.ui.models.ResultDetailsMovies
import com.example.moviesdirector.view.ui.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("/3/")
    fun getMoviesNetwork(
        @Query(Constants.TRENDING) trending: String?,
        @Query(Constants.TYPE) type: String?,
        @Query(Constants.TEMP) temp: String?,
        @Query(Constants.API_KEY) api_key: String?,
        @Query(Constants.PAGE) page: String?,
        @Query(Constants.LANGUAGE) languege: String?,
        @Query(Constants.PARAMETER) parameter: String?
    ): Call<ResultDetailsMovies>
}

//https://api.themoviedb.org/
// 3/
// trending/
// movie/
// week?
// api_key=319f821ad80072b8c1dd98a08e31346c&
// page=1&
// language=pt-BR&
// append_to_response=images