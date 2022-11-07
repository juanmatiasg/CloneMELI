package com.example.cleanarquitecture_mercadolibre.data.network

import com.example.cleanarquitecture_mercadolibre.data.model.DescriptionModel
import com.example.cleanarquitecture_mercadolibre.data.model.ListPicturesModel
import com.example.cleanarquitecture_mercadolibre.data.model.ListProduct
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiClient {
    @GET("sites/MLA/search?q=")
    suspend fun getResult(@Query("q") q: String): ListProduct

    @GET("items/{id}/description")
    suspend fun getDescription(@Path("id") id: String): DescriptionModel

    @GET("items/{id}")
    suspend fun getPictures(@Path("id") id: String): ListPicturesModel


}