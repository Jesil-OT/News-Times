package com.jesil.toborowei.newstimes.presentation.fragments.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.databinding.HomeFragmentBinding

class HomeFragment: Fragment(R.layout.home_fragment) {
    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = HomeFragmentBinding.bind(view)
        binding.apply {
            //   TODO("Do something inside here")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}