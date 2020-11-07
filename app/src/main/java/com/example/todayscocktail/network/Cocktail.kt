package com.example.todayscocktail.network

import com.squareup.moshi.Json

class Cocktail (
    @Json(name = "idDrink")
    val id: Int,
    @Json(name = "idDrink")
    val name: String,
    @Json(name = "idDrink")
    val thumbUrl: String
)