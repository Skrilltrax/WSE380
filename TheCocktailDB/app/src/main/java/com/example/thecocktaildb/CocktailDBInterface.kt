package com.example.thecocktaildb

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailDBInterface {

    @GET("v1/1/filter.php")
    fun getDrinksFromIngredient(@Query("i") ingredient: String): Call<CocktailModel>
}