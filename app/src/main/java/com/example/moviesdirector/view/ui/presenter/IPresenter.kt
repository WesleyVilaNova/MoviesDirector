package com.example.moviesdirector.view.ui.presenter

import com.example.moviesdirector.view.ui.models.ModelResultDetails
import com.example.moviesdirector.view.ui.models.Result

interface IPresenter{

    interface obtemAPI {
        fun getCallRetrofit()
    }

    interface ContratoView {
        fun viewAPI(listMovies: List<ModelResultDetails>)
        fun viewError()
    }

}