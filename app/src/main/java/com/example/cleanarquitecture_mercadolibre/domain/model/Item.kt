package com.example.cleanarquitecture_mercadolibre.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(val id:String,var title: String, var price: Double, var thumbnail: String): Parcelable



