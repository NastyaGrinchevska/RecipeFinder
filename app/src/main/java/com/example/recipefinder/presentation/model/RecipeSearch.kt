package com.example.recipefinder.presentation.model

import java.io.Serializable

data class RecipeSearch(
    val type: String,
    val keyWord: String
): Serializable