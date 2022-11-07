package com.example.cleanarquitecture_mercadolibre.data.model

import com.google.gson.annotations.SerializedName

data class ListPicturesModel(
    @SerializedName("pictures")
    var pictures:ArrayList<PicturesModel>
)