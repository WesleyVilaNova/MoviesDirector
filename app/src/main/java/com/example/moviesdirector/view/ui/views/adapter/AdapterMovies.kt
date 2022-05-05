package com.example.moviesdirector.view.ui.views.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdirector.databinding.ListRecyclerMoviesBinding
import com.example.moviesdirector.view.ui.models.Result
import com.squareup.picasso.Picasso


class AdapterMovies(private var listMovies: List<Result>?, val btnKnow : (Intent) -> Unit?) :
    RecyclerView.Adapter<AdapterMovies.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ListRecyclerMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemMovie = listMovies?.get(position)

        Picasso.get().load(itemMovie?.getPostImg()).into(holder.binding.imageViewPosterPath)
        if (itemMovie != null) {
            holder.binding.titleMovies.text = itemMovie.title
            holder.binding.textViewVoteAverage.text = itemMovie.vote_average.toString()
            holder.binding.textViewDetailsMovies.text = itemMovie.overview
            //Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(imageView);

            holder.binding.btnKnowMore.setOnClickListener { btnKnow (Intent()) }
        }
    }

    override fun getItemCount(): Int {
        return listMovies?.size ?: 0
    }


    class MyViewHolder(val binding: ListRecyclerMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}