package com.example.cleanarquitecture_mercadolibre.data.repository

import com.example.cleanarquitecture_mercadolibre.data.model.ListPicturesModel
import com.example.cleanarquitecture_mercadolibre.data.model.PicturesModel
import com.example.cleanarquitecture_mercadolibre.data.network.ApiService
import com.example.cleanarquitecture_mercadolibre.domain.model.Pictures

class PictureRepository(private val apiService: ApiService) {
    suspend fun getPicturesFromApi(id:String): List<Pictures> {
        val response: ArrayList<PicturesModel> = apiService.getPictures(id).pictures
        return response.map { it.toDomain() }
    }
    private fun PicturesModel.toDomain(): Pictures {
        return Pictures(secure_url)
    }
}