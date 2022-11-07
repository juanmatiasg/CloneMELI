package com.example.cleanarquitecture_mercadolibre.data.model

import com.google.gson.annotations.SerializedName

data class ItemModel(
    @SerializedName("id")
    var id:String,
    @SerializedName("title")
    var title:String,
    @SerializedName("price")
    var price:Double,
    @SerializedName("thumbnail")
    var thumbnail:String,
    @SerializedName("pictures")
    var listPictures:String

)
