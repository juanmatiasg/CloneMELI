package com.example.cleanarquitecture_mercadolibre.ui.homeFragment

import androidx.lifecycle.*
import com.example.cleanarquitecture_mercadolibre.data.database.entities.ProductEntity
import com.example.cleanarquitecture_mercadolibre.vo.Resource
import com.example.cleanarquitecture_mercadolibre.domain.usecases.GetResultCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(private val getResultCase: GetResultCase) :ViewModel(){

    private val term = MutableLiveData<String>()


   val fetchProduct = term.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(getResultCase.getResult(it)))
            } catch (e: Exception) {
                emit(Resource.error(data = null, message = e.message ?: "Ocurrio un error"))
            }
        }
    }

    fun findByProduct(otherTerm:String){
        term.value = otherTerm
    }


    fun addFavourite(product:ProductEntity){
        viewModelScope.launch {
            getResultCase.addFavourite(product = product)
        }
    }

}