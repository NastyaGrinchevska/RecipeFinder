package com.example.recipefinder.data.model

import com.example.recipefinder.data.model.meal.Meals
import com.example.recipefinder.data.model.recipe.Recipe

sealed class ApiResult {

    class SearchSuccess(val meals: Meals) : ApiResult()

    class SearchError(val message: String) : ApiResult()

    class RecipeSuccess(val recipe: Recipe) : ApiResult()

    class RecipeError(val message: String) : ApiResult()
}


