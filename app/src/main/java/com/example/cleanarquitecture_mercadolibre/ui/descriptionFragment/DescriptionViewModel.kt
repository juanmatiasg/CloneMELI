package com.example.cleanarquitecture_mercadolibre.ui.descriptionFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.cleanarquitecture_mercadolibre.data.database.entities.ProductEntity
import com.example.cleanarquitecture_mercadolibre.data.repository.DescriptionRepository
import com.example.cleanarquitecture_mercadolibre.domain.model.Description
import com.example.cleanarquitecture_mercadolibre.domain.usecases.GetDescriptionCase
import com.example.cleanarquitecture_mercadolibre.domain.usecases.GetPictureCase
import com.example.cleanarquitecture_mercadolibre.domain.usecases.GetResultCase
import com.example.cleanarquitecture_mercadolibre.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class DescriptionViewModel(private val useCase: GetDescriptionCase,private val useCasePicture: GetPictureCase,private val getResultCase: GetResultCase): ViewModel() {

    fun getDescription(id:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(useCase.getDescription(id).plain_text))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Ocurrio un error"))
        }
    }

    fun getPictures(id:String)= liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(useCasePicture.getPicture(id)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Ocurrio un error"))
        }
    }

    fun saveFavourite(productEntity: ProductEntity){
        viewModelScope.launch {
            getResultCase.addFavourite(productEntity)
        }
    }

}