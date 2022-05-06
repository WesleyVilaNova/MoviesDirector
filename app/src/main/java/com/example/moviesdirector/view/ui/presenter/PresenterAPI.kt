package com.example.moviesdirector.view.ui.presenter

import android.util.Log
import com.example.moviesdirector.view.ui.models.ModelResultDetails
import com.example.moviesdirector.view.ui.repository.ConfigRetrofit
import com.example.moviesdirector.view.ui.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterAPI(_view: IPresenter.ContratoView) : IPresenter.getObtemAPI {

    private val view: IPresenter.ContratoView = _view

    override fun getCallRetrofit() {
        Log.i("TAG", "chegou no getCall")
        val call: Call<ModelResultDetails> = ConfigRetrofit()
            .getService
            .getMoviesNetwork(
                Constants.API_KEY,
                Constants.PAGE,
                Constants.LANGUAGE
            )
        call.enqueue(object : Callback<ModelResultDetails> {
            override fun onResponse(
                call: Call<ModelResultDetails>,
                response: Response<ModelResultDetails>
            ) {
                if (response.isSuccessful) {
                    val resultado: ModelResultDetails? = response.body()
                    view.viewAPI(resultado?.results)
                }
            }

            override fun onFailure(call: Call<ModelResultDetails>, t: Throwable) {
                Log.i("TAG", "Error ${t.message}")
                view.viewError()
            }

        })
    }


}