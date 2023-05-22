package com.example.recipefinder.presentation.result

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipefinder.R
import com.example.recipefinder.data.model.meal.Meals

class SearchResultAdapter(private val meals: Meals, private val onRecipeClickListener: OnRecipeClickListener) : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeImageView: ImageView = itemView.findViewById(R.id.recipeImageView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val recipeLayout: LinearLayout = itemView.findViewById(R.id.recipeLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.search_result_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = meals.meals!!.get(position)

        Glide.with(holder.recipeImageView.context).load(meal.strMealThumb)
            .into(holder.recipeImageView)

        holder.nameTextView.text = meal.strMeal

        holder.recipeLayout.setOnClickListener {
            onRecipeClickListener.onClick(meal.idMeal.toInt())
        }
    }

    override fun getItemCount(): Int {
        return meals.meals!!.size
    }

}