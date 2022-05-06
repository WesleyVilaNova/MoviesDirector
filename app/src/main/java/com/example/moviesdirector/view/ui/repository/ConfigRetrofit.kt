package com.example.moviesdirector.view.ui.repository

import com.example.moviesdirector.view.ui.interfaces.WebService
import com.example.moviesdirector.view.ui.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigRetrofit {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val getService: WebService by lazy {
        retrofit.create(WebService::class.java)
    }
}