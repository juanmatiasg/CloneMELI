package com.example.cleanarquitecture_mercadolibre.data.model

import com.google.gson.annotations.SerializedName

data class ListProduct(
    @SerializedName("results")
    var lista: ArrayList<ItemModel>
)