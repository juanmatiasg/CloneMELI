package com.example.cleanarquitecture_mercadolibre.data.network

import com.example.cleanarquitecture_mercadolibre.core.RetrofitHelper
import com.example.cleanarquitecture_mercadolibre.data.model.DescriptionModel
import com.example.cleanarquitecture_mercadolibre.data.model.ListPicturesModel
import com.example.cleanarquitecture_mercadolibre.data.model.ListProduct
import com.example.cleanarquitecture_mercadolibre.data.model.PicturesModel
import com.example.cleanarquitecture_mercadolibre.domain.model.Description


class ApiService {

    suspend fun getResult(query:String): ListProduct = RetrofitHelper.webService.getResult(query)

    suspend fun getDescription(id:String): DescriptionModel = RetrofitHelper.webService.getDescription(id)

    suspend fun getPictures(id:String): ListPicturesModel = RetrofitHelper.webService.getPictures(id)

}