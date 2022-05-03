package com.example.moviesdirector.view.ui.presenter

interface IPresenter{

    interface obtemAPI {
        fun getCallRetrofit()
    }

    interface ContratoView {
        fun viewAPI()
        fun viewError()
    }

}