package com.example.parsinglocaljsonfile

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parsinglocaljsonfile.databinding.ImageRowBinding

class RecyclerViewAdapter(private val activity:MainActivity, private var images: ArrayList<Image>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ImageRowBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        Log.d("main", ".onCreateViewHolder new view requested")
        return ItemViewHolder(
            ImageRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val photo = images[position]

        holder.binding.apply {
            Glide.with(activity).load(photo.url).into(imageView)
        }
    }
    override fun getItemCount() = images.size
}