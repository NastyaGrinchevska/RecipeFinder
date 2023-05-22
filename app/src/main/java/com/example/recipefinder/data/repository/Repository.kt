package com.example.recipefinder.data.repository

import com.example.recipefinder.data.cloud.Api
import com.example.recipefinder.data.model.ApiResult
import java.lang.Exception
import java.net.UnknownHostException

interface Repository {

    suspend fun searchByName(name: String): ApiResult

    suspend fun searchByCountry(country: String): ApiResult

    suspend fun searchByMainIngredient(ingredient: String): ApiResult

    suspend fun getRecipeById(id: Int): ApiResult

    suspend fun randomRecipe(): ApiResult


    class Impl(private val api: Api) : Repository {

        override suspend fun searchByName(name: String): ApiResult {
            return try {
                ApiResult.SearchSuccess(api.searchByName(name))
            } catch (e: Exception) {
                ApiResult.SearchError(getErrorMessage(e))
            }
        }

        override suspend fun searchByCountry(country: String): ApiResult {
            return try {
                ApiResult.SearchSuccess(api.searchByCountry(country))
            } catch (e: Exception) {
                ApiResult.SearchError(getErrorMessage(e))
            }
        }

        override suspend fun searchByMainIngredient(ingredient: String): ApiResult {
            return try {
                ApiResult.SearchSuccess(api.searchByMainIngredient(ingredient))
            } catch (e: Exception) {
                ApiResult.SearchError(getErrorMessage(e))
            }
        }

        override suspend fun getRecipeById(id: Int): ApiResult {
            return try {
                ApiResult.RecipeSuccess(api.getRecipeById(id))
            } catch (e: Exception) {
                ApiResult.RecipeError(getErrorMessage(e))
            }
        }

        override suspend fun randomRecipe(): ApiResult {
            return try {
                ApiResult.RecipeSuccess(api.randomRecipe())
            } catch (e: Exception) {
                ApiResult.RecipeError(getErrorMessage(e))
            }
        }

        private fun getErrorMessage(e: Exception): String {
            return if (e is UnknownHostException) {
                "No Internet connection!"
            } else {
                e.message.toString()
            }
        }

    }

}