package com.example.xml.utils

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.xml.MainActivity
import com.example.xml.R
import com.example.xml.views.components.CardViewHolder
import com.example.xml.views.Detail
import timber.log.Timber

class MovieAdapter(private var movies: List<MovieEntity>) :
    RecyclerView.Adapter<CardViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card, viewGroup, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CardViewHolder, position: Int) {
        val movie = movies[position]
        setupCardContents(viewHolder, movie)
    }

    override fun getItemCount() = movies.size

    fun updateMovies(newMovies: List<MovieEntity>) {
        this.movies = newMovies
        notifyDataSetChanged()

        for (movie in newMovies) {
            Timber.d("Movie cached/loaded to RecyclerView: ID=%d, Title=%s", movie.id, movie.title)
        }
    }

    private fun setupCardContents(viewHolder: CardViewHolder, movie: MovieEntity) {
        val context = viewHolder.itemView.context

        viewHolder.title.text = movie.title
        viewHolder.description.text = movie.overview

        viewHolder.productImg.load(movie.posterPath) {
            crossfade(true)
            placeholder(R.drawable.ss)
            error(R.drawable.ss)
            transformations(RoundedCornersTransformation(16f))
        }

        viewHolder.wikiBtn.setOnClickListener {
            Timber.i("Wiki/Web button pressed for Movie: %s", movie.title)
            val queryUrl = "https://www.themoviedb.org/search?query=${movie.title.replace(" ", "+")}"
            val intent = Intent(Intent.ACTION_VIEW, queryUrl.toUri())
            context.startActivity(intent)
        }

        viewHolder.detailBtn.setOnClickListener {
            Timber.i("Details button pressed for movie: %s", movie.title)

            val activity = context as? MainActivity
            val detailFragment = Detail()

            val bundle = Bundle().apply {
                putString("title", movie.title)
                putString("overview", movie.overview)
                putString("posterPath", movie.posterPath)
            }
            detailFragment.arguments = bundle

            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragmentContainerView, detailFragment)
                addToBackStack(null)
                commit()
            }
        }
    }
}