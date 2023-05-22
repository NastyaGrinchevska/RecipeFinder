package com.example.recipefinder.presentation.result

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefinder.data.cloud.Retrofit
import com.example.recipefinder.data.model.ApiResult
import com.example.recipefinder.data.repository.Repository
import com.example.recipefinder.databinding.FragmentSearchResultBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchResultViewModel : ViewModel() {

    private val repository: Repository = Repository.Impl(Retrofit.api)

    val progressLiveData = MutableLiveData<Int>()
    val searchResultLiveData = MutableLiveData<ApiResult>()

    fun searchByName(name: String) {
        progressLiveData.postValue(View.VISIBLE)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.searchByName(name)
            searchResultLiveData.postValue(result)
            progressLiveData.postValue(View.GONE)
        }
    }

    fun searchByCountry(country: String) {
        progressLiveData.postValue(View.VISIBLE)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.searchByCountry(country)
            searchResultLiveData.postValue(result)
            progressLiveData.postValue(View.GONE)
        }
    }

    fun searchByMainIngredient(mainIngredient: String) {
        progressLiveData.postValue(View.VISIBLE)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.searchByMainIngredient(mainIngredient)
            searchResultLiveData.postValue(result)
            progressLiveData.postValue(View.GONE)
        }
    }

}