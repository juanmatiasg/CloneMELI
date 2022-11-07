package com.example.cleanarquitecture_mercadolibre.ui.homeFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarquitecture_mercadolibre.data.database.entities.ProductEntity
import com.example.cleanarquitecture_mercadolibre.databinding.FragmentHomeBinding
import com.example.cleanarquitecture_mercadolibre.domain.model.Item
import com.example.cleanarquitecture_mercadolibre.ui.adapter.AdapterProduct
import com.example.cleanarquitecture_mercadolibre.vo.Status
import com.example.cleanarquitecture_mercadolibre.vo.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(){

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<HomeViewModel>()
    private lateinit var mAdapter: AdapterProduct


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchView()
        setupObserver()

    }

    private fun search(term: String) {
        activity?.hideKeyboard()
        viewModel.findByProduct(term)
    }

    private fun setupObserver() {
        viewModel.fetchProduct.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    Log.e("DATA_LOADING", it.data.toString())
                    setupVisibility(false)

                }
                Status.SUCCESS -> {
                    Log.e("DATA_SUCCESS", it.data.toString())

                    setupVisibility(true)
                    setupRecylerView(it.data as ArrayList<Item>)
                }
                Status.ERROR -> {
                    Log.e("DATA_ERROR", it.data.toString())

                }
            }
        })
    }

    private fun setupSearchView() {
        binding.searchView.rootSearchView.apply { executeSearchButton.setOnClickListener { search(searchInputText.text.toString()) } }
    }

    private fun setupVisibility(isVisible: Boolean) {
        if (!isVisible) {
            binding.rvProducts.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.rvProducts.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setupRecylerView(data:ArrayList<Item>) {
        binding.rvProducts.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            mAdapter = AdapterProduct(data)
            adapter = mAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}