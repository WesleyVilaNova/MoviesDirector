package com.example.moviesdirector.view.ui.views.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesdirector.R
import com.example.moviesdirector.databinding.ActivityDetailsMoviesBinding
import com.example.moviesdirector.view.ui.models.Result
import com.example.moviesdirector.view.ui.utils.Constants
import com.squareup.picasso.Picasso

class DetailsMoviesActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailsMoviesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        clickMenu()
        recuperando()
        btn_watch()
    }

    private fun btn_watch() {
        binding.btnWatch.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(Constants.NETFLIX)
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

    private fun recuperando() {
        val movie: Result? = intent.getSerializableExtra("key") as Result?

        if (movie != null) {
            binding.textViewDetailsMovies.text = movie.overview
            binding.titleMovies.text = movie.title
            binding.popularity.text = movie.popularity
            binding.releaseDate.text = movie.release_date
            binding.voteAverage.rating = (movie.vote_average ?: 0) as Float

            Picasso.get().load(movie.getPostImgDetails()).into(binding.imageViewBackdropPath)
        }
    }
}
