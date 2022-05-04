package com.example.moviesdirector.view.ui.interfaces

import com.example.moviesdirector.view.ui.models.ModelResultDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("3/trending/movie/week?")
    fun getMoviesNetwork(
        @Query("api_key") api_key: String?,
        @Query("page") page: String?,
        @Query("language") languege: String?,
    ): Call <ModelResultDetails>
}
//https://api.themoviedb.org/3/trending/movie/week?api_key=319f821ad80072b8c1dd98a08e31346c&page=1&language=pt-BR
//https://api.themoviedb.org/
// 3/
// trending/
// movie/
// week?
// api_key=319f821ad80072b8c1dd98a08e31346c&
// page=1&
// language=pt-BR&
// append_to_response=images