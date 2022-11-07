package com.example.cleanarquitecture_mercadolibre.domain.usecases

import com.example.cleanarquitecture_mercadolibre.data.database.entities.ProductEntity
import com.example.cleanarquitecture_mercadolibre.data.repository.ResultRepository
import com.example.cleanarquitecture_mercadolibre.domain.model.Item

class GetResultCase(private val repository: ResultRepository) {

    suspend fun getResult(query:String): List<Item> { return repository.getResultFromApi(query) }

    suspend fun addFavourite(product:ProductEntity) { return repository.insertProduct(product) }

    suspend fun deleteFavourite(product:ProductEntity) { return repository.deleteProduct(product) }

    suspend fun getAllFavourite(): List<Item> {return repository.getAllResultFromDatabase() }

    //fun Item.toDatabase() = ProductEntity(id,title, price, thumbnail)
}