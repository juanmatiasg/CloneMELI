package com.example.cleanarquitecture_mercadolibre.data.database.dao

import androidx.room.*
import com.example.cleanarquitecture_mercadolibre.data.database.entities.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductEntity")
    suspend fun getAllProduct(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(product: ProductEntity)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)
}