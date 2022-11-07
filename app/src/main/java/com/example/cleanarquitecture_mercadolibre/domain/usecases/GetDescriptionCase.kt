package com.example.cleanarquitecture_mercadolibre.domain.usecases

import com.example.cleanarquitecture_mercadolibre.data.repository.DescriptionRepository
import com.example.cleanarquitecture_mercadolibre.domain.model.Description

class GetDescriptionCase(private val repository: DescriptionRepository) {
    suspend fun getDescription(id:String):Description{
        return repository.getDescriptionFromApi(id)
    }
}