package com.example.moviesdirector.view.ui.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdirector.databinding.ListRecyclerMoviesBinding
import com.example.moviesdirector.view.ui.models.ModelResultDetails
import com.example.moviesdirector.view.ui.models.Result
import com.squareup.picasso.Picasso


lateinit var result: Result

class AdapterMovies(private var listMovies: List<ModelResultDetails>?) :
    RecyclerView.Adapter<AdapterMovies.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ListRecyclerMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemMovie = listMovies?.get(position)

        Picasso.get().load(result.getPostImg()).into(holder.binding.imageViewPosterPath)
        if (itemMovie != null) {
            holder.binding.titleMovies.text = itemMovie.results[position].title
            holder.binding.textViewVoteAverage.text =
                itemMovie.results[position].vote_average.toString()
            holder.binding.textViewDetailsMovies.text = itemMovie.results[position].overview
            //Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(imageView);
        }
    }

    override fun getItemCount(): Int {
        return listMovies?.size ?: 0
    }


    class MyViewHolder(val binding: ListRecyclerMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}