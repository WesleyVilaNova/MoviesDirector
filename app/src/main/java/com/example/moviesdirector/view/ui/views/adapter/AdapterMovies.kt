package com.example.moviesdirector.view.ui.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdirector.databinding.ListRecyclerMoviesBinding
import com.example.moviesdirector.view.ui.models.Result
import com.squareup.picasso.Picasso


class AdapterMovies(private var listMovies: List<Result>?, private val clickMovie: Onclick) :
    RecyclerView.Adapter<AdapterMovies.MyViewHolder>() {

    interface Onclick {
        fun onClickKnowMovie(movie: Result?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ListRecyclerMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemMovie = listMovies?.get(position)

        itemMovie?.let {

            Picasso.get().load(itemMovie.getPostImg()).into(holder.binding.imageViewPosterPath)
            holder.binding.titleMovies.text = itemMovie.title
            holder.binding.releaseDateRecycler.text = itemMovie.release_date
            holder.binding.textViewDetailsMovies.text = itemMovie.overview

        }
        holder.binding.btnKnowMore.setOnClickListener { clickMovie.onClickKnowMovie(itemMovie) }
    }

    override fun getItemCount(): Int {
        return listMovies?.size ?: 0
    }

    class MyViewHolder(val binding: ListRecyclerMoviesBinding) :
        RecyclerView.ViewHolder(binding.root)

}