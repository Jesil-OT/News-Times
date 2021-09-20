package com.jesil.toborowei.newstimes.presentation.fragments.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.databinding.SearchFragmentBinding

class SearchFragment: Fragment(R.layout.search_fragment) {
    private val searchViewModel: SearchViewModel by viewModels()
    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SearchFragmentBinding.bind(view)
        binding.apply {
            //   TODO("Do something inside here")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}