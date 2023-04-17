package com.example.drinksapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinksService {

    @GET("v1/1/filter.php")
    fun getDrinksByIngredient(@Query("i") ingredient: String): Call<DrinksModel>
}