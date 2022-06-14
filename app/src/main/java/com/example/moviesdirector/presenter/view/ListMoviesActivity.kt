package com.example.moviesdirector.presenter.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesdirector.R
import com.example.moviesdirector.data.api.WebService
import com.example.moviesdirector.data.repository.ListMoviesRepository
import com.example.moviesdirector.databinding.ActivityListMoviesBinding
import com.example.moviesdirector.domain.exceptions.ErrorHandling
import com.example.moviesdirector.domain.models.ModelListMovies
import com.example.moviesdirector.domain.usecase.ListMoviesUseCase
import com.example.moviesdirector.domain.utils.Constants
import com.example.moviesdirector.presenter.viewmodel.ListMoviesViewModel
import com.example.moviesdirector.view.ui.views.adapter.AdapterMovies

class ListMoviesActivity : AppCompatActivity(), Onclick {

    private lateinit var binding: ActivityListMoviesBinding
    private lateinit var viewModel: ListMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityListMoviesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ListMoviesViewModel.MainViewModelFactory(
                ListMoviesUseCase(
                    ListMoviesRepository(WebService.getInstanceBaseUrl())
                )
            )
        )[ListMoviesViewModel::class.java]

        setupMenu()
        callingRequestApi()
        setupObservers()
    }

    private fun setupMenu() {
        binding.includeDetails.ibMenu.setOnClickListener {
            val popupMenu = PopupMenu(this, binding.includeDetails.ibMenu)
            popupMenu.menuInflater.inflate(R.menu.itens_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
                if (menuItem.itemId == R.id.it_sobre) {
                    val intent = Intent(this, AboutScreenActivity::class.java)
                    startActivity(intent)
                } else if (menuItem.itemId == R.id.netflix) {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(Constants.NETFLIX)
                    startActivity(intent)
                }
                true
            }
            popupMenu.show()
        }
    }

    private fun callingRequestApi() {
        viewModel.getListMovies()
    }

    private fun setupObservers() {
        viewModel.errorMsg.observe(this) { messageError ->
            setupError(messageError)
        }
        viewModel.listMovies.observe(this) { resultListMovie ->
            loadingListMovie(resultListMovie)
        }
    }

    private fun setupError(messageError: ErrorHandling<String>?) {
        when (messageError) {
            is ErrorHandling.Error -> {
                Toast.makeText(this, messageError.exception.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loadingListMovie(resultListMovie: ErrorHandling<List<ModelListMovies>>) {
        binding.recyclerViewList.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewList.setHasFixedSize(true)
        val adapter = AdapterMovies(this)
        when (resultListMovie) {

            is ErrorHandling.Loading -> {
                binding.progressList.visibility = View.VISIBLE
            }

            is ErrorHandling.Success -> {
                binding.progressList.visibility = View.INVISIBLE
                adapter.submitList(resultListMovie.listMovies)
            }
        }
        binding.recyclerViewList.adapter = adapter
    }

    override fun onClickKnowMovie(movie: ModelListMovies?) {
        val intent = Intent(this, DetailsMoviesActivity::class.java)
        intent.putExtra(Constants.KEY_INTENT, movie?.id)
        startActivity(intent)
    }
}
