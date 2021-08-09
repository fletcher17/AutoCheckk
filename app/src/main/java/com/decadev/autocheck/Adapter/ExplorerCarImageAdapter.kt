package com.decadev.autocheck.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.decadev.autocheck.ClickListener.CarClickListener
import com.decadev.autocheck.Model.Cars.Result
import com.decadev.autocheck.R
import com.decadev.autocheck.databinding.CarImagesLayoutBinding

class ExplorerCarImageAdapter(val carData: List<Result>, var itemclick: CarClickListener) : RecyclerView.Adapter<ExplorerCarImageAdapter.ExplorerCarImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExplorerCarImageViewHolder {
        val binding = CarImagesLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ExplorerCarImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExplorerCarImageViewHolder, position: Int) {
        val carResultsData = carData[position]

        holder.bind(carResultsData, itemclick)
    }

    override fun getItemCount(): Int = carData.size

    inner class ExplorerCarImageViewHolder(val binding: CarImagesLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(results: Result, clickListener: CarClickListener) {
            Glide.with(binding.root).load(results.imageUrl).apply(
                RequestOptions().placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)).into(binding.carImageView)
            binding.modelTextView.text = results.title
            binding.stateTextView.text = "${results.city} , ${results.state}"
            binding.priceTextView.text = results.marketplacePrice.toString()

            binding.carImageView.setOnClickListener {
                clickListener.onClickItem(results)
            }
        }
    }
}