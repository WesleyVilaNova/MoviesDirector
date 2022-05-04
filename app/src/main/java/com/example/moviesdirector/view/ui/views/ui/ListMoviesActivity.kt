package com.example.moviesdirector.view.ui.views.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdirector.databinding.ActivityListMoviesBinding
import com.example.moviesdirector.view.ui.models.ModelResultDetails
import com.example.moviesdirector.view.ui.models.Result
import com.example.moviesdirector.view.ui.presenter.IPresenter
import com.example.moviesdirector.view.ui.presenter.PresenterAPI
import com.example.moviesdirector.view.ui.views.adapter.AdapterMovies

class ListMoviesActivity : AppCompatActivity(), IPresenter.ContratoView {

    lateinit var adapter : AdapterMovies
    private lateinit var recyclerView: RecyclerView
    private var listMovies : List<ModelResultDetails>? = null

    lateinit var binding: ActivityListMoviesBinding
    var view: IPresenter.obtemAPI = PresenterAPI(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityListMoviesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        chamandoRetrofit()


    }


    private fun chamandoRetrofit() {
        view.getCallRetrofit()
    }

    override fun viewAPI(listMovies: List<ModelResultDetails>) {
        Log.i("TAG", "******************** Chegou ViewAPI")
        val newList : List<ModelResultDetails> = listMovies
        adapter = AdapterMovies(newList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = AdapterMovies(newList)


//        adapter = AdapterMovies(listMovies)
//        binding.recyclerViewList.layoutManager = LinearLayoutManager(this)
//        binding.recyclerViewList.setHasFixedSize(true)
//        adapter.notifyDataSetChanged()
        Log.i("TAG", "******************** Chegou ViewAPI")

    }


    override fun viewError() {
        Toast.makeText(this, "Desculpe, ocorreu um erro ao consultar", Toast.LENGTH_LONG).show()
    }
}