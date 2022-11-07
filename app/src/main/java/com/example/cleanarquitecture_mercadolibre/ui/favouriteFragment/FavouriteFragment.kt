package com.example.cleanarquitecture_mercadolibre.ui.favouriteFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarquitecture_mercadolibre.R
import com.example.cleanarquitecture_mercadolibre.data.database.entities.ProductEntity
import com.example.cleanarquitecture_mercadolibre.databinding.FragmentFavouriteBinding
import com.example.cleanarquitecture_mercadolibre.domain.model.Item
import com.example.cleanarquitecture_mercadolibre.ui.adapter.AdapterFavourite
import com.example.cleanarquitecture_mercadolibre.ui.adapter.AdapterProduct
import com.example.cleanarquitecture_mercadolibre.ui.homeFragment.HomeViewModel
import com.example.cleanarquitecture_mercadolibre.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavouriteFragment : Fragment(),AdapterFavourite.OnFavouriteListener {

    private  var _binding:FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapterFavourite: AdapterFavourite

    private val viewModel by viewModel<ViewModelFavourite>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.getProductFavoritos().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {}
                Status.SUCCESS -> {
                    mAdapterFavourite = AdapterFavourite(it.data as ArrayList<Item>,this)
                    binding.rvFavourite.adapter = mAdapterFavourite
                    mAdapterFavourite.notifyDataSetChanged()
                }
                Status.ERROR -> {}
            }
        }
    }


    private fun setupRecyclerView() {
        binding.rvFavourite.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun deleteProduct(item: Item, position: Int) {
        viewModel.deleteFavourite(ProductEntity(item.id,item.title,item.price,item.thumbnail))
    }

}