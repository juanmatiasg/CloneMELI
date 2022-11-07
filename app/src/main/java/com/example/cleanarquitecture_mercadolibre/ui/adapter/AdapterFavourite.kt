package com.example.cleanarquitecture_mercadolibre.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarquitecture_mercadolibre.databinding.ItemFavouriteBinding
import com.example.cleanarquitecture_mercadolibre.domain.model.Item
import com.squareup.picasso.Picasso

class AdapterFavourite(
    private var item: ArrayList<Item>,
    private val itemClickListener: OnFavouriteListener
) : RecyclerView.Adapter<AdapterFavourite.FavouriteViewHolder>() {

    lateinit var binding: ItemFavouriteBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        binding = ItemFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteViewHolder(binding)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        binding.textViewId.text = item[position].id.toString()
        binding.textViewTItle.text = item[position].title
        binding.textViewPrecio.text = item[position].price.toString()
        binding.imageView.loadUrl(item[position].thumbnail)

        binding.btnDelete.setOnClickListener {
            itemClickListener.deleteProduct(item[position], position)
            item.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeRemoved(position, itemCount)
        }

    }

    inner class FavouriteViewHolder(binding: ItemFavouriteBinding) :
        RecyclerView.ViewHolder(binding.root)


    fun ImageView.loadUrl(url: String) {
        Picasso.get().load(url).into(binding.imageView)
    }

    fun setItems(otherItem: ArrayList<Item>) {
        item.clear()
        item.addAll(otherItem)
        notifyDataSetChanged()
    }

    interface OnFavouriteListener {
        fun deleteProduct(item: Item, position: Int)
    }
}