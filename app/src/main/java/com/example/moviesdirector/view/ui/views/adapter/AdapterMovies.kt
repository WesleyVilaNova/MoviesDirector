package com.example.moviesdirector.view.ui.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdirector.databinding.ListRecyclerMoviesBinding
import com.example.moviesdirector.view.ui.interfaces.Onclick
import com.example.moviesdirector.view.ui.models.ModelListMovies
import com.squareup.picasso.Picasso

class AdapterMovies(private val clickMovie: Onclick) :
    ListAdapter<ModelListMovies, AdapterMovies.MyViewHolder>(DiffCallBack()), Onclick {

    override fun onClickKnowMovie(movie: ModelListMovies?) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ListRecyclerMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemMovie = getItem(position)

        itemMovie?.let {
            Picasso.get().load(itemMovie.getPostImg()).into(holder.binding.imageViewPosterPath)

            holder.binding.titleMovies.text = itemMovie.title
            holder.binding.releaseDateRecycler.text = itemMovie.release_date
            holder.binding.textViewDetailsMovies.text = itemMovie.overview
        }
        holder.binding.btnKnowMore.setOnClickListener { clickMovie.onClickKnowMovie(itemMovie) }
    }

    class MyViewHolder(val binding: ListRecyclerMoviesBinding) :
        RecyclerView.ViewHolder(binding.root)
}

class DiffCallBack : DiffUtil.ItemCallback<ModelListMovies>() {
    override fun areItemsTheSame(oldItem: ModelListMovies, newItem: ModelListMovies): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ModelListMovies, newItem: ModelListMovies): Boolean {
        return oldItem.overview == newItem.overview && oldItem.id == newItem.id
    }
}
