package com.example.cleanarquitecture_mercadolibre.ui.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarquitecture_mercadolibre.R
import com.example.cleanarquitecture_mercadolibre.databinding.ItemsBinding
import com.example.cleanarquitecture_mercadolibre.domain.model.Item
import com.squareup.picasso.Picasso

class AdapterProduct(private val item:ArrayList<Item>):RecyclerView.Adapter<AdapterProduct.ProductViewHolder>() {

    lateinit var binding :ItemsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        binding = ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }



    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        binding.textViewId.text = item[position].id
        binding.textViewTItle.text = item[position].title
        binding.textViewPrecio.text = item[position].price.toString()
        binding.imageView.loadUrl(item[position].thumbnail)

        binding.cardView.setOnClickListener {
            val bundle= Bundle()
          //  bundle.putParcelable(PRODUCT_ITEMS,item[position])
         //   it.findNavController().navigate(R.id.action_homeFragment_to_descriptionFragment, bundleOf("KEY_ID" to binding.textViewId.text.toString()))
              it.findNavController().navigate(R.id.action_homeFragment_to_descriptionFragment, bundleOf( PRODUCT_ITEMS to item[position]))


        }


    }

    companion object{
        const val PRODUCT_ITEMS ="PRODUCT_ITEMS"
    }



    inner class ProductViewHolder(binding: ItemsBinding) :RecyclerView.ViewHolder(binding.root)


    fun ImageView.loadUrl(url:String){ Picasso.get().load(url).into(binding.imageView) }

}