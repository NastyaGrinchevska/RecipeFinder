package com.example.recipefinder.data.model.recipe

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("meals")
    val recipeInfo: List<RecipeInfo>
)