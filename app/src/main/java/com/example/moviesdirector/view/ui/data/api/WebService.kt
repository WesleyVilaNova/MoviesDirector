package com.example.moviesdirector.view.ui.data.api

import com.example.moviesdirector.view.ui.domain.models.ModelDetailsMovies
import com.example.moviesdirector.view.ui.domain.models.ModelMovies
import com.example.moviesdirector.view.ui.domain.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {
    @GET(Constants.PATH_URL)
    fun getListMoviesNetwork(
        @Query("api_key") api_key: String?,
        @Query("page") page: String?,
        @Query("language") languege: String?,
    ): Call<ModelMovies?>

    @GET(Constants.PATH_URL_DETAILS)
    fun getDetailsMoviesNetWork(
        @Path("id") id: Int?,
        @Query("api_key") api_key: String?,
        @Query("language") languege: String?
    ): Call<ModelDetailsMovies>

    companion object {
        private val retrofitService: WebService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(WebService::class.java)
        }

        fun getInstanceBaseUrl(): WebService {
            return retrofitService
        }
    }
}
