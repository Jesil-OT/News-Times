package com.jesil.toborowei.newstimes.presentation.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.data.models.NewsResponse
import com.jesil.toborowei.newstimes.databinding.HomeFragmentBinding
import com.jesil.toborowei.newstimes.presentation.utils.DataResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.home_fragment) {
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()


    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = HomeFragmentBinding.bind(view)
        binding.apply {
            //   TODO("Do something inside here")
        }
        homeViewModel.topHeadlines.observe(viewLifecycleOwner){
            when(it){
                is DataResult.Success -> {binding.dataResult.text = "${it.data?.articles}"
                    Log.d("okhttp", "data: ${it.data}")
                }
                is DataResult.Loading -> binding.dataResult.text = "Loading..."
                is DataResult.Error -> binding.dataResult.text = it.error.localizedMessage
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}