package com.example.thecocktaildb

data class CocktailModel(
    val drinks: List<Drink>,
)

data class Drink(
    val strDrink: String,
    val strDrinkThumb: String,
    val idDrink: String,
)
