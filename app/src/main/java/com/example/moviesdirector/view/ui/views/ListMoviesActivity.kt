package com.example.moviesdirector.view.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviesdirector.R
import com.example.moviesdirector.view.ui.presenter.IPresenter

class ListMoviesActivity : AppCompatActivity() , IPresenter.ContratoView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movies)
    }

    override fun viewAPI() {
        TODO("Not yet implemented")
    }

    override fun viewError() {
        TODO("Not yet implemented")
    }
}