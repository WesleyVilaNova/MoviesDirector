package com.example.moviesdirector.view.ui.presenter

import android.util.Log
import com.example.moviesdirector.view.ui.models.ResultDetailsMovies
import com.example.moviesdirector.view.ui.repository.ConfigRetrofit
import com.example.moviesdirector.view.ui.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterAPI(_view: IPresenter.ContratoView) : IPresenter.obtemAPI {

    private val view: IPresenter.ContratoView = _view
    var teste: String = ""


    override fun getCallRetrofit() {
        val call: Call<ResultDetailsMovies> = ConfigRetrofit()
            .getService
            .getMoviesNetwork(
                Constants.TRENDING,
                Constants.TYPE,
                Constants.TEMP,
                Constants.API_KEY,
                Constants.PAGE,
                Constants.LANGUAGE,
                Constants.PARAMETER
            )
        call.enqueue(object : Callback<ResultDetailsMovies> {
            override fun onResponse(
                call: Call<ResultDetailsMovies>,
                response: Response<ResultDetailsMovies>
            ) {
                if (response.isSuccessful) {
                    teste = response.body().toString()
                    Log.i("TAG", "********************$teste")
                }
            }

            override fun onFailure(call: Call<ResultDetailsMovies>, t: Throwable) {
                view.viewError()
            }

        })
    }

}