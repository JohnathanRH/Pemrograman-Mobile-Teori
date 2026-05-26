package com.example.recyclerviewexcercise

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial

class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val productImg: ImageView
    val title: TextView
    val subtitle: TextView
    val switch: SwitchMaterial
    val actionBtn: Button
    val cardView: CardView

    init {
        productImg = view.findViewById(R.id.productImg)
        title = view.findViewById(R.id.title)
        subtitle = view.findViewById(R.id.subtitle)
        switch = view.findViewById(R.id.cardSwitch)
        actionBtn = view.findViewById(R.id.cardActionBtn)
        cardView = view.findViewById(R.id.cardView)
    }

    public fun bind(item: ItemModel, index: Int){
        val context = view.context

        productImg.setImageDrawable(context.getDrawable(item.imgResourceId))
        title.text = item.title
        subtitle.text = item.subTitle

        switch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                Toast.makeText(
                    context,
                    "Switch hidup pada item $index",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        actionBtn.setOnClickListener {
            Toast.makeText(
                context,
                "Tombol telah ditekan untuk tombol $index",
                Toast.LENGTH_SHORT
            ).show()
        }

        cardView.setOnClickListener {
            Toast.makeText(
                context,
                "Item telah ditekan untuk $index",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}