package com.example.cleanarquitecture_mercadolibre.domain.usecases

import com.example.cleanarquitecture_mercadolibre.data.repository.PictureRepository
import com.example.cleanarquitecture_mercadolibre.domain.model.Pictures

class GetPictureCase(private val repository: PictureRepository) {
    suspend fun getPicture(id:String):List<Pictures> =  repository.getPicturesFromApi(id)

}