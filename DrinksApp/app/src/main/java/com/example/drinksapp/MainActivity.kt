package com.example.drinksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var inputEditText: EditText
    lateinit var submitButton: Button

    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.thecocktaildb.com/api/json/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val drinksService = retrofit.create(DrinksService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        setupListeners()
    }

    fun initializeViews() {
        inputEditText = findViewById(R.id.input)
        submitButton = findViewById(R.id.submit)
    }

    fun setupListeners() {
        submitButton.setOnClickListener { view ->
            val ingredient = inputEditText.text.toString()
            if (ingredient.isEmpty()) {
                Toast.makeText(this@MainActivity, "Error: Input is empty", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            val ingredientCall = drinksService.getDrinksByIngredient(ingredient)
            ingredientCall.enqueue(object : Callback<DrinksModel> {
                override fun onResponse(call: Call<DrinksModel>, response: Response<DrinksModel>) {
                    val drinks = response.body()!!.drinks
                    Log.d("onResponse", drinks.toString())
                }

                override fun onFailure(call: Call<DrinksModel>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity,
                        "Error: Cannot get data from the server",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }
}