package com.example.recipefinder.presentation.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.recipefinder.data.model.ApiResult
import com.example.recipefinder.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {

    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!
    private val args: RecipeFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[RecipeViewModel::class.java]

        if (args.recipeId == -1) {
            viewModel.randomRecipe()
        } else {
            viewModel.recipeById(args.recipeId)
        }

        viewModel.progressLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = it
        }

        viewModel.recipeLiveData.observe(viewLifecycleOwner) {
            if (it is ApiResult.RecipeSuccess) {
                binding.instructionsTitleTextView.visibility = View.VISIBLE
                binding.areaTitleTextView.visibility = View.VISIBLE
                binding.categoryTitleTextView.visibility = View.VISIBLE

                Glide.with(requireContext()).load(it.recipe.recipeInfo.first().strMealThumb).into(binding.recipeImageView)
                binding.nameTextView.text = it.recipe.recipeInfo.first().strMeal
                binding.areaTextView.text = it.recipe.recipeInfo.first().strArea
                binding.caregoryTextView.text = it.recipe.recipeInfo.first().strCategory
                binding.instructionsTextView.text = it.recipe.recipeInfo.first().strInstructions
            } else if (it is ApiResult.RecipeError) {
                binding.errorTextView.visibility = View.VISIBLE
                binding.backButton.visibility = View.VISIBLE
                binding.instructionsTitleTextView.visibility = View.GONE
                binding.areaTitleTextView.visibility = View.GONE
                binding.categoryTitleTextView.visibility = View.GONE
                binding.imageCardView.visibility = View.GONE
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}