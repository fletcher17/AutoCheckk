package com.decadev.autocheck.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.decadev.autocheck.Model.logo.Make
import com.decadev.autocheck.R
import com.decadev.autocheck.databinding.CarLogosLayoutBinding

class ExplorerLogoAdapter(val brandLogos: List<Make>) :
    RecyclerView.Adapter<ExplorerLogoAdapter.ExplorerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExplorerViewHolder {
        val binding =
            CarLogosLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ExplorerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExplorerViewHolder, position: Int) {
        val carLogoList = brandLogos[position]

        holder.bind(carLogoList)
    }

    override fun getItemCount(): Int = brandLogos.size

    inner class ExplorerViewHolder(val binding: CarLogosLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(make: Make) {
            Glide.with(binding.root).load(make.imageUrl).apply(
                RequestOptions().placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            ).into(binding.imageLogo)
            binding.logoTextView.text = make.name
        }
    }
}