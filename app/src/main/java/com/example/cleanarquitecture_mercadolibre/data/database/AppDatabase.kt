package com.example.cleanarquitecture_mercadolibre.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cleanarquitecture_mercadolibre.data.database.dao.ProductDao
import com.example.cleanarquitecture_mercadolibre.data.database.entities.ProductEntity

@Database(entities = [ProductEntity::class],version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "DBProduct"
            ).build()
            return INSTANCE!!
        }
    }
}