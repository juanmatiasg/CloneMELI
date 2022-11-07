package com.example.cleanarquitecture_mercadolibre.ui.favouriteFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.cleanarquitecture_mercadolibre.data.database.entities.ProductEntity
import com.example.cleanarquitecture_mercadolibre.domain.usecases.GetResultCase
import com.example.cleanarquitecture_mercadolibre.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelFavourite(private val getResultCase: GetResultCase):ViewModel() {

    fun getProductFavoritos() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(getResultCase.getAllFavourite()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Ocurrio un error"))
        }
    }

    fun deleteFavourite(product: ProductEntity){
        viewModelScope.launch {
            getResultCase.deleteFavourite(product)
        }
    }
}