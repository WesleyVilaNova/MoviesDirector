package com.example.moviesdirector.view.ui.views.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesdirector.R
import com.example.moviesdirector.databinding.ActivityListMoviesBinding
import com.example.moviesdirector.view.ui.models.Result
import com.example.moviesdirector.view.ui.presenter.IPresenter
import com.example.moviesdirector.view.ui.presenter.PresenterAPI
import com.example.moviesdirector.view.ui.views.adapter.AdapterMovies

class ListMoviesActivity : AppCompatActivity(), IPresenter.ContratoView, AdapterMovies.Onclick {
    var view: IPresenter.getObtemAPI = PresenterAPI(this)

    private lateinit var binding: ActivityListMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityListMoviesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        chamandoRetrofit()

    }

    private fun chamandoRetrofit() {
        view.getCallRetrofit()
    }

    override fun viewAPI(listMovies: List<Result>?) {
        binding.recyclerViewList.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewList.setHasFixedSize(true)
        binding.recyclerViewList.adapter = AdapterMovies(listMovies, this)
    }


    override fun viewError() {
        Toast.makeText(this, getString(R.string.msg_error), Toast.LENGTH_LONG).show()
    }

    override fun onClickKnowMovie(movie: Result?) {
        val intent = Intent(this, DetailsMoviesActivity::class.java)
        intent.putExtra("key", movie)
        startActivity(intent)
    }
}
