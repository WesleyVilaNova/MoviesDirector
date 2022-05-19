package com.example.moviesdirector.view.ui.views.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.moviesdirector.R
import com.example.moviesdirector.databinding.ActivityDetailsMoviesBinding
import com.example.moviesdirector.view.ui.interfaces.WebService
import com.example.moviesdirector.view.ui.models.ModelDetailsMovies
import com.example.moviesdirector.view.ui.repository.MainRepository
import com.example.moviesdirector.view.ui.utils.Constants
import com.example.moviesdirector.view.ui.viewmodel.DetailsViewModel
import com.example.moviesdirector.view.ui.viewmodel.DetailsViewModelFactory
import com.squareup.picasso.Picasso

class DetailsMoviesActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsMoviesBinding
    lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailsMoviesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            DetailsViewModelFactory(MainRepository(WebService.getInstance()))
        )[DetailsViewModel::class.java]

        clickMenu()
        recuperandoId()
    }

    private fun btn_watch(url: ModelDetailsMovies?) {
        binding.btnWatch.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            try {
                intent.data = Uri.parse(url?.getUrlDetailsMovie())
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.erro_url_watch), Toast.LENGTH_LONG).show()
            }
            startActivity(intent)
        }
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

    private fun recuperandoId() {
        val id = intent.getIntExtra(Constants.KEY_INTENT, 0)
        id.let { viewModel.getDetailsMovies(id) }
        dataMovieDetails()
    }

    private fun dataMovieDetails() {
        viewModel.viewDetailsListMovies.observe(this) { listMoviesDetails ->

            Picasso.get().load(listMoviesDetails?.getPostImgDetails()).into(binding.imageViewBackdropPath)
            binding.titleMovies.text = listMoviesDetails?.title
            binding.textTagLine.text = listMoviesDetails?.tagline
            binding.voteAverage.rating = listMoviesDetails?.vote_average!!.toFloat()
            binding.textViewDetailsMovies.text = listMoviesDetails.overview
            binding.popularity.text = listMoviesDetails.popularity
            try {
                binding.textGenresOne.text = listMoviesDetails.genres[0].genero1
                binding.textGenresTwo.text = listMoviesDetails.genres[1].genero1
                binding.textGenresThree.text = listMoviesDetails.genres[2].genero1
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.generos_error), Toast.LENGTH_LONG).show()
            }
            btn_watch(listMoviesDetails)
        }
    }
}
