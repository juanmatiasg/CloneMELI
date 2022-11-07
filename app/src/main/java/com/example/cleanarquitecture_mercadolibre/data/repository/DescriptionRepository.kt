package com.example.cleanarquitecture_mercadolibre.data.repository

import com.example.cleanarquitecture_mercadolibre.data.model.DescriptionModel
import com.example.cleanarquitecture_mercadolibre.data.model.ItemModel
import com.example.cleanarquitecture_mercadolibre.data.model.ListPicturesModel
import com.example.cleanarquitecture_mercadolibre.data.model.PicturesModel
import com.example.cleanarquitecture_mercadolibre.data.network.ApiService
import com.example.cleanarquitecture_mercadolibre.domain.model.Description
import com.example.cleanarquitecture_mercadolibre.domain.model.Item
import com.example.cleanarquitecture_mercadolibre.domain.model.Pictures

class DescriptionRepository(private val apiService: ApiService) {

    suspend fun getDescriptionFromApi(id:String): Description {
        val response = apiService.getDescription(id)
        return response.toDomain()
    }

    private fun DescriptionModel.toDomain(): Description {
        return Description(plain_text)
    }



}