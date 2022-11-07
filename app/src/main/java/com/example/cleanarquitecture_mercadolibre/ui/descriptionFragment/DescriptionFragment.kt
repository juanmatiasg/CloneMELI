package com.example.cleanarquitecture_mercadolibre.ui.descriptionFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarquitecture_mercadolibre.data.database.entities.ProductEntity
import com.example.cleanarquitecture_mercadolibre.databinding.FragmentDescriptionBinding
import com.example.cleanarquitecture_mercadolibre.domain.model.Item
import com.example.cleanarquitecture_mercadolibre.domain.model.Pictures
import com.example.cleanarquitecture_mercadolibre.ui.adapter.AdapterPictures
import com.example.cleanarquitecture_mercadolibre.ui.adapter.AdapterProduct
import com.example.cleanarquitecture_mercadolibre.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel


class DescriptionFragment : Fragment() {


    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapterPictures: AdapterPictures
    private lateinit var item: Item

    private val viewModel by viewModel<DescriptionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireArguments().let {
            item = it.getParcelable(AdapterProduct.PRODUCT_ITEMS)!!
        }
        setupObserverDescription()
        setupObserverPictures()
        saveFavourite()
    }

    private fun saveFavourite() {
        requireArguments().let {
            binding.btnSaveFavourite.setOnClickListener {
                viewModel.saveFavourite(ProductEntity(item.id,item.title,item.price,item.thumbnail))
            }
        }
    }

    private fun setupObserverPictures() {
        viewModel.getPictures(item.id).observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    Log.e("LOADING_PICTURES", it.data.toString())
                }

                Status.SUCCESS -> {
                    Log.e("SUCCESS_PICTURES", it.data.toString())
                    setupRecyclerView(it.data)
                }
                Status.ERROR -> {
                    Log.e("ERROR_PICTURES", it.data.toString())
                }
            }
        }
    }

    private fun setupRecyclerView(data: List<Pictures>?) {
        binding.rvPictures.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            mAdapterPictures = AdapterPictures(data as ArrayList<Pictures>)
            adapter = mAdapterPictures
        }
    }

    private fun setupObserverDescription() {
        viewModel.getDescription(item.id).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    Log.e("LOADING_DESCRIPTION", it.data.toString())

                }
                Status.SUCCESS -> {
                    Log.e("SUCCESS_DESCRIPTION", it.data.toString())
                    binding.tvDescription.text = it.data!!

                }
                Status.ERROR -> {
                    Log.e("ERROR_DESCRIPTION", it.data.toString())
                }
            }
        })
        
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}