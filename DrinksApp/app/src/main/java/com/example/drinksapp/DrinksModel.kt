package com.example.drinksapp

data class DrinksModel(
    val drinks: List<Drink>,
)

data class Drink(
    val strDrink: String,
    val strDrinkThumb: String?,
    val idDrink: String,
)
