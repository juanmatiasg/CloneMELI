package com.example.cleanarquitecture_mercadolibre.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarquitecture_mercadolibre.R
import com.example.cleanarquitecture_mercadolibre.databinding.ItemPicturesBinding
import com.example.cleanarquitecture_mercadolibre.databinding.ItemsBinding
import com.example.cleanarquitecture_mercadolibre.domain.model.Item
import com.example.cleanarquitecture_mercadolibre.domain.model.Pictures
import com.squareup.picasso.Picasso

class AdapterPictures(private val itemPictures :ArrayList<Pictures>):RecyclerView.Adapter<AdapterPictures.PictureViewHolder>(){

    private lateinit var binding:ItemPicturesBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        binding = ItemPicturesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PictureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        binding.imageView.loadUrl(itemPictures[position].secure_url)
    }


    override fun getItemCount(): Int = itemPictures.size

    fun ImageView.loadUrl(url:String){ Picasso.get().load(url).into(binding.imageView) }

    inner class PictureViewHolder( binding:ItemPicturesBinding):RecyclerView.ViewHolder(binding.root)
}
