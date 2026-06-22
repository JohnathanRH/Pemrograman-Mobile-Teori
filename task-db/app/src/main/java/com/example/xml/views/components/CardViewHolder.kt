package com.example.xml.views.components

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xml.R

class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val productImg: ImageView = view.findViewById(R.id.productImg)
    val title: TextView = view.findViewById(R.id.cardProductTitle)
    val description: TextView = view.findViewById(R.id.cardProductDescription)
    val wikiBtn: Button = view.findViewById(R.id.wikiBtn)
    val detailBtn: Button = view.findViewById(R.id.detailBtn)
}