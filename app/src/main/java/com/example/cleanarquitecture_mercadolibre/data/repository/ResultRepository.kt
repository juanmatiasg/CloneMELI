package com.example.cleanarquitecture_mercadolibre.data.repository

import com.example.cleanarquitecture_mercadolibre.data.database.dao.ProductDao
import com.example.cleanarquitecture_mercadolibre.data.database.entities.ProductEntity
import com.example.cleanarquitecture_mercadolibre.data.model.ItemModel
import com.example.cleanarquitecture_mercadolibre.data.network.ApiService
import com.example.cleanarquitecture_mercadolibre.domain.model.Item

class ResultRepository(private val api: ApiService,private val productDao: ProductDao) {


    suspend fun getResultFromApi(query: String): List<Item> {
        val response: ArrayList<ItemModel> = api.getResult(query).lista
        return response.map { it.toDomain() }
    }

    private fun ItemModel.toDomain(): Item {
        return Item(id,title, price, thumbnail)
    }

    suspend fun getAllResultFromDatabase():List<Item>{
        val response: List<ProductEntity> = productDao.getAllProduct()
        return response.map { it.toDomain() }
    }

    private fun ProductEntity.toDomain(): Item {
        return Item(id.toString(),title, price, thumbnail)
    }

    suspend fun insertProduct(product: ProductEntity){
        productDao.insertFavourite(product)
    }

    suspend fun deleteProduct(product:ProductEntity){
        productDao.deleteProduct(product)
    }


}



