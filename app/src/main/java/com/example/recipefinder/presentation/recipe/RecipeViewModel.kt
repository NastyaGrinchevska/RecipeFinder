package com.example.recipefinder.presentation.recipe

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefinder.data.cloud.Retrofit
import com.example.recipefinder.data.model.ApiResult
import com.example.recipefinder.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    private val repository = Repository.Impl(Retrofit.api)

    val progressLiveData = MutableLiveData<Int>()
    val recipeLiveData = MutableLiveData<ApiResult>()

    fun randomRecipe() {
        progressLiveData.postValue(View.VISIBLE)
        viewModelScope.launch(Dispatchers.IO){
            val result = repository.randomRecipe()
            recipeLiveData.postValue(result)
            progressLiveData.postValue(View.GONE)
        }
    }

    fun recipeById(id: Int) {
        progressLiveData.postValue(View.VISIBLE)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getRecipeById(id)
            recipeLiveData.postValue(result)
            progressLiveData.postValue(View.GONE)
        }
    }

}