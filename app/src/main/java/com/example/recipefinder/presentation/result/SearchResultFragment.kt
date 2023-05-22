package com.example.recipefinder.presentation.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipefinder.data.model.ApiResult
import com.example.recipefinder.databinding.FragmentSearchResultBinding


class SearchResultFragment : Fragment(), OnRecipeClickListener {

    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!

    private val args: SearchResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.searchResultRecyclerView.layoutManager = layoutManager
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.searchResultRecyclerView.addItemDecoration(dividerItemDecoration)


        val viewModel = ViewModelProvider(this)[SearchResultViewModel::class.java]

        when (args.recipeSearch.type) {
            "name" -> viewModel.searchByName(args.recipeSearch.keyWord)
            "country" -> viewModel.searchByCountry(args.recipeSearch.keyWord)
            else -> viewModel.searchByMainIngredient(args.recipeSearch.keyWord)
        }

        viewModel.progressLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = it
        }
        viewModel.searchResultLiveData.observe(viewLifecycleOwner) {
            if (it is ApiResult.SearchSuccess) {
                if (it.meals.meals != null)
                    binding.searchResultRecyclerView.adapter = SearchResultAdapter(it.meals, this)
                else {
                    binding.errorTextView.visibility = View.VISIBLE
                    binding.backButton.visibility = View.VISIBLE
                }
            } else if (it is ApiResult.SearchError) {
                binding.errorTextView.text = it.message
                binding.errorTextView.visibility = View.VISIBLE
                binding.backButton.visibility = View.VISIBLE
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onClick(recipeId: Int) {
        findNavController().navigate(
            SearchResultFragmentDirections.actionSearchResultFragmentToRecipeFragment(recipeId)
        )
    }
}

interface OnRecipeClickListener {
    fun onClick(recipeId: Int)
}