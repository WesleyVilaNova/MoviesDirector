package com.example.moviesdirector.view.ui.interfaces

import com.example.moviesdirector.view.ui.models.ModelResultDetails
import com.example.moviesdirector.view.ui.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("3/trending/movie/week?")
    fun getMoviesNetwork(
        @Query("api_key") api_key: String?,
        @Query("page") page: String?,
        @Query("language") languege: String?,
    ): Call<ModelResultDetails?>

    companion object {

        private val retrofitService: WebService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(WebService::class.java)

        }

        fun getInstance(): WebService {
            return retrofitService
        }

    }
}
