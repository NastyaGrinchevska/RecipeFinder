package com.example.recipefinder.presentation.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipefinder.databinding.FragmentMainBinding
import com.example.recipefinder.presentation.model.RecipeSearch

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.nameLayout.setOnClickListener {
            recipeSearch("name")
        }
        binding.countryLayout.setOnClickListener {
            recipeSearch("country")
        }
        binding.mainIngredientLayout.setOnClickListener {
            recipeSearch("main ingredient")
        }
        binding.randomLayout.setOnClickListener {
            randomRecipe()
        }
    }

    private fun recipeSearch(searchType: String) {
        val editText = EditText(context)
        editText.setPadding(30, 30, 30, 30)

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Enter $searchType:")
        builder.setView(editText)

        builder.setPositiveButton("Search") { _, _ ->
            val keyWord = editText.text.toString()
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToSearchResultFragment(
                   RecipeSearch(searchType, keyWord)
                )
            )
        }
        builder.setNegativeButton("Cancel", null)
        val dialog = builder.create()

        dialog.show()
    }

    private fun randomRecipe() {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToRecipeFragment(-1)
        )
    }

}