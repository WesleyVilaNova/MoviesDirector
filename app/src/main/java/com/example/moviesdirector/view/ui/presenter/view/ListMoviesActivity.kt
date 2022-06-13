package com.example.moviesdirector.view.ui.presenter.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesdirector.R
import com.example.moviesdirector.databinding.ActivityListMoviesBinding
import com.example.moviesdirector.view.ui.domain.Onclick
import com.example.moviesdirector.view.ui.data.api.WebService
import com.example.moviesdirector.view.ui.domain.models.ModelListMovies
import com.example.moviesdirector.view.ui.data.repository.MainRepository
import com.example.moviesdirector.view.ui.domain.utils.Constants
import com.example.moviesdirector.view.ui.presenter.viewmodel.ListMoviesViewModel
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
            ListMoviesViewModel.MainViewModelFactory(MainRepository(retrofit = WebService.getInstanceBaseUrl()))
        )[ListMoviesViewModel::class.java]

        chamandoRetrofit()
        clickMenu()
    }

    private fun clickMenu() {
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

    private fun chamandoRetrofit() {
        binding.recyclerViewList.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewList.setHasFixedSize(true)
        viewModel.getListMovies()
        viewModel.listMovies.observe(this) {
            val adapter = AdapterMovies(this)
            adapter.submitList(it)
            binding.recyclerViewList.adapter = adapter
        }

        viewModel.errorMsg.observe(this) {
            Toast.makeText(this, R.string.msg_error, Toast.LENGTH_LONG).show()
        }
    }

    override fun onClickKnowMovie(movie: ModelListMovies?) {
        val intent = Intent(this, DetailsMoviesActivity::class.java)
        intent.putExtra(Constants.KEY_INTENT, movie?.id)
        startActivity(intent)
    }
}
