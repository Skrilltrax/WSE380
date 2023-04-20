package com.example.thecocktaildb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class DrinksList : AppCompatActivity() {
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drinks_list)
        val json = intent.getStringExtra("drinks")
        val drinksAdapter = moshi.adapter(CocktailModel::class.java)

        val cocktailModel = drinksAdapter.fromJson(json!!)
        val drinks: List<Drink> = cocktailModel!!.drinks

        val itemClickListener: (drink: Drink) -> Unit = {
            Toast.makeText(this@DrinksList, it.strDrink, Toast.LENGTH_LONG).show()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        val adapter = DrinksListAdapter(drinks, itemClickListener)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}