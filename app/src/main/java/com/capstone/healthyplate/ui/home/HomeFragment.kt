package com.capstone.healthyplate.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.healthyplate.databinding.FragmentHomeBinding
import com.capstone.healthyplate.model.RecipeListAdapter
import com.capstone.healthyplate.ui.generate.GenerateActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var recipeAdapter: RecipeListAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnGenerateHome.setOnClickListener { toGenerateActivity() }

        homeViewModel.recipeRV.observe(viewLifecycleOwner) { recipe ->
            recipeAdapter = RecipeListAdapter(recipe)
            binding.rvRecipeHome.layoutManager = LinearLayoutManager(requireContext())
            binding.rvRecipeHome.setHasFixedSize(true)
            binding.rvRecipeHome.adapter = recipeAdapter
        }
        return root
    }

    private fun toGenerateActivity() {
        val intent = Intent (activity, GenerateActivity::class.java)
        activity?.startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}