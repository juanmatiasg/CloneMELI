package com.example.cleanarquitecture_mercadolibre.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name="id")
    var id:String,
    @ColumnInfo(name="title")
    var title:String,
    @ColumnInfo(name="price")
    var price:Double,
    @ColumnInfo(name="thumbnail")
    var thumbnail:String
)