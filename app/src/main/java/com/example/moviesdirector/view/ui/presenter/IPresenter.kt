package com.example.moviesdirector.view.ui.presenter

import com.example.moviesdirector.view.ui.models.Result

interface IPresenter{

    interface getObtemAPI {
        fun getCallRetrofit()
    }

    interface ContratoView {
        fun viewAPI(listMovies: List<Result>?)
        fun viewError()
    }

}