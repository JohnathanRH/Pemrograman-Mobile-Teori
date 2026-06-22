package com.example.xml

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.xml.models.Game
import com.example.xml.views.components.CardViewHolder
import com.example.xml.views.Detail
import timber.log.Timber

class RecyclerViewAdapter(private var games: List<Game>) :
    RecyclerView.Adapter<CardViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card, viewGroup, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CardViewHolder, position: Int) {
        val game = games[position]
        setupCardContents(viewHolder, game)
    }

    override fun getItemCount() = games.size

    fun updateGames(newGames: List<Game>) {
        this.games = newGames
        notifyDataSetChanged()

        for (game in newGames) {
            Timber.d(
                "Game added to RecyclerView: ID=%d, TitleRes=%d",
                game.id,
                game.titleResource
            )
        }
    }

    fun setupCardContents(viewHolder: CardViewHolder, game : Game){
        val context = viewHolder.itemView.context

        viewHolder.title.text = context.getString(game.titleResource)
        viewHolder.description.text = context.getString(game.descResource)
        viewHolder.productImg.setImageDrawable(context.getDrawable(game.imgResource))

        viewHolder.wikiBtn.setOnClickListener {
            Timber.i(
                "Wiki button pressed for Game: %d",
                game.id
            )
            val intent = Intent(Intent.ACTION_VIEW, game.wikiUri.toUri())
            context.startActivity(intent)
        }

        viewHolder.detailBtn.setOnClickListener {
            Timber.i("Details button pressed.")
            Timber.d(
                "Game -> ID: %d, TitleRes: %d, WikiURL: %s",
                game.id,
                game.titleResource,
                game.wikiUri
            )
            val activity = context as? MainActivity
            val detailFragment = Detail()

            val bundle = Bundle().apply {
                putInt("titleResource", game.titleResource)
                putInt("descResource", game.descResource)
                putInt("imgResource", game.imgResource)
                putString("wikiUri", game.wikiUri)
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