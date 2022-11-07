package com.example.cleanarquitecture_mercadolibre.di

import android.app.Application
import com.example.cleanarquitecture_mercadolibre.data.database.AppDatabase
import com.example.cleanarquitecture_mercadolibre.data.database.dao.ProductDao
import com.example.cleanarquitecture_mercadolibre.data.repository.ResultRepository
import com.example.cleanarquitecture_mercadolibre.data.network.ApiService
import com.example.cleanarquitecture_mercadolibre.data.repository.DescriptionRepository
import com.example.cleanarquitecture_mercadolibre.data.repository.PictureRepository
import com.example.cleanarquitecture_mercadolibre.domain.usecases.GetDescriptionCase
import com.example.cleanarquitecture_mercadolibre.domain.usecases.GetPictureCase
import com.example.cleanarquitecture_mercadolibre.domain.usecases.GetResultCase
import com.example.cleanarquitecture_mercadolibre.ui.descriptionFragment.DescriptionViewModel
import com.example.cleanarquitecture_mercadolibre.ui.favouriteFragment.ViewModelFavourite
import com.example.cleanarquitecture_mercadolibre.ui.homeFragment.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp:Application() {


    val apiModule = module {

        single {ApiService()}

        viewModel{ViewModelFavourite(get())}

        single{PictureRepository(get())}
        single{GetPictureCase(get())}

        single{DescriptionRepository(get())}
        single { GetDescriptionCase(get()) }

        viewModel{DescriptionViewModel(get(),get(),get())}


        single<ProductDao>{AppDatabase.getDatabase(get()).productDao()}

        single { ResultRepository(get(),get()) }
        single{ GetResultCase(get()) }
        viewModel { HomeViewModel(get()) }

    }

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@MyApp)
            modules(apiModule)
        }
    }


}