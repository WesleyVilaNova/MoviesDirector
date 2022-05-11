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
<<<<<<< HEAD


=======
//https://api.themoviedb.org/3/trending/movie/week?api_key=&page=1&language=pt-BR
//https://api.themoviedb.org/
// 3/
// trending/
// movie/
// week?
// api_key=&
// page=1&
// language=pt-BR&
// append_to_response=images
>>>>>>> 39b487c612bc9ce364814d4e7f4bc137acbb8ee1
