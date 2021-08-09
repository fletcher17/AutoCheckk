package com.decadev.autocheck.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.decadev.autocheck.Model.CarMediaLists.CarMediaX
import com.decadev.autocheck.R
import com.decadev.autocheck.databinding.CarTypesImagesBinding

class MediaAdapter(var carMedia: List<CarMediaX>) : RecyclerView.Adapter<MediaAdapter.MediaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val binding = CarTypesImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MediaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val carMediaList = carMedia[position]
        holder.bind(carMediaList)
    }

    override fun getItemCount(): Int = carMedia.size

    inner class MediaViewHolder(val binding: CarTypesImagesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(carMediaX: CarMediaX) {
            binding.carTypesNameTextView.text = carMediaX.name
            binding.carModelTextView.text = carMediaX.type
            Glide.with(binding.root).load(carMediaX.url).apply(
                RequestOptions().placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)).into(binding.carTypesImageView)
        }
    }
}