package com.example.recipefinder.data.cloud

import com.example.recipefinder.data.model.meal.Meals
import com.example.recipefinder.data.model.recipe.Recipe
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {

    @Headers("Content-Type: application/json")
    @GET("search.php")
    suspend fun searchByName(@Query("s") name: String): Meals

    @Headers("Content-Type: application/json")
    @GET("filter.php")
    suspend fun searchByCountry(@Query("a") country: String): Meals

    @Headers("Content-Type: application/json")
    @GET("filter.php")
    suspend fun searchByMainIngredient(@Query("i") ingredient: String): Meals

    @Headers("Content-Type: application/json")
    @GET("lookup.php")
    suspend fun getRecipeById(@Query("i") id: Int): Recipe

    @Headers("Content-Type: application/json")
    @GET("random.php")
    suspend fun randomRecipe(): Recipe

}