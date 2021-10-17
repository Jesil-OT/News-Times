package com.jesil.toborowei.newstimes.presentation.fragments.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.databinding.CategoriesFragmentBinding
import com.jesil.toborowei.newstimes.presentation.utils.adapter.categories_adapter.CategoriesViewPagerAdapter

class CategoriesFragment: Fragment(R.layout.categories_fragment) {
    private val categoriesViewModel: CategoriesViewModel by viewModels()
    private var _binding: CategoriesFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = CategoriesFragmentBinding.bind(view)
        val viewPagerAdapter = CategoriesViewPagerAdapter(requireActivity())
        binding.categoriesViewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.categoriesTabLayout, binding.categoriesViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "business"
                1 -> tab.text = "entertainment"
                2 -> tab.text = "general"
                3 -> tab.text = "health"
                4 -> tab.text = "science"
                5 -> tab.text = "sports"
                6 -> tab.text = "technology"
            }
        }.attach()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}