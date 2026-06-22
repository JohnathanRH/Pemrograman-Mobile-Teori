package com.example.xml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation

class CarouselAdapter(private var imageUrls: List<String>) :
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    class CarouselViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.carousel_item, parent, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.imageView.load(imageUrls[position]) {
            crossfade(true)
            placeholder(R.drawable.ss)
            error(R.drawable.ss)
            transformations(RoundedCornersTransformation(16f))
        }
    }

    override fun getItemCount(): Int = imageUrls.size
    fun updateImages(newUrls: List<String>) {
        this.imageUrls = newUrls
        notifyDataSetChanged()
    }
}