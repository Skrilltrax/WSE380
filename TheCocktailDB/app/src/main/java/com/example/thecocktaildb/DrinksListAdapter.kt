package com.example.thecocktaildb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

class DrinksListAdapter(val items: List<Drink>, val itemClickListener: (drink: Drink) -> Unit) : RecyclerView.Adapter<DrinksListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return DrinksListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: DrinksListViewHolder, position: Int) {
        holder.bind(items[position], itemClickListener)
    }
}

class DrinksListViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(drink: Drink, itemClickListener: (drink: Drink) -> Unit) {
        itemView.setOnClickListener { itemClickListener(drink) }
        val textView = itemView.findViewById<TextView>(R.id.tv)
        val imageView = itemView.findViewById<ShapeableImageView>(R.id.img)

        textView.text = drink.strDrink
        Glide.with(itemView).load(drink.strDrinkThumb).into(imageView)
    }
}