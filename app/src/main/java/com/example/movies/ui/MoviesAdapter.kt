package com.example.movies.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.databinding.FilmsListItemBinding
import com.example.movies.response.Film

class MoviesAdapter(private val filmsList: List<Film>) :
    RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {

    class MovieHolder(val binding: FilmsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val movieName = binding.movieName
        val movieImage = binding.movieImage
        val rootContainer = binding.rootContainer
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FilmsListItemBinding.inflate(layoutInflater, parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.movieName.text = filmsList[position].name
        Glide.with(holder.binding.root.context)
            .load(filmsList[position].image_url)
            .into(holder.movieImage)
        holder.rootContainer.setOnClickListener {
            filmsList[position].onClick()
        }
    }

    override fun getItemCount(): Int {
        return filmsList.size
    }

}