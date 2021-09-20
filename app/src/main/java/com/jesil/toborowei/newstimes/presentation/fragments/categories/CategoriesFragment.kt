package com.jesil.toborowei.newstimes.presentation.fragments.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.databinding.CategoriesFragmentBinding

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
        binding.apply {
            //   TODO("Do something inside here")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}