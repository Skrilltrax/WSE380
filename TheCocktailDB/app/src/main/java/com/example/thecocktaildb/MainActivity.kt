package com.example.thecocktaildb

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {
    lateinit var editTextView: EditText
    lateinit var submitButton: Button
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.thecocktaildb.com/api/json/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val cocktailDBInterface = retrofit.create<CocktailDBInterface>(CocktailDBInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        setupListeners()
    }

    fun initializeViews() {
        editTextView = findViewById(R.id.editTv)
        submitButton = findViewById(R.id.submitBtn)
    }

    fun setupListeners() {
        submitButton.setOnClickListener { view ->
            val message: String = editTextView.text.toString()
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()

            Log.d("MainActivity", message)

            cocktailDBInterface.getDrinksFromIngredient(message)
                .enqueue(object : Callback<CocktailModel> {
                    override fun onResponse(
                        call: Call<CocktailModel>,
                        response: Response<CocktailModel>
                    ) {
                        val list = response.body()?.drinks ?: return
                        val intent = Intent(this@MainActivity, DrinksList::class.java)
                        val drinksAdapter = moshi.adapter(CocktailModel::class.java)
                        val json = drinksAdapter.toJson(response.body())
                        Log.d("onResponse", json)
                        intent.putExtra("drinks", json)
                        startActivity(intent)
                    }

                    override fun onFailure(call: Call<CocktailModel>, t: Throwable) {
                        Log.d("MainActivity", t.message ?: "Failure occurred")
                    }
                })
        }
    }
}