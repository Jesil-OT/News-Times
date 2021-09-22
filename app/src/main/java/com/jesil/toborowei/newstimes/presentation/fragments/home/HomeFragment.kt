package com.jesil.toborowei.newstimes.presentation.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.data.models.NewsResponse
import com.jesil.toborowei.newstimes.databinding.HomeFragmentBinding
import com.jesil.toborowei.newstimes.presentation.utils.DataResult
import com.jesil.toborowei.newstimes.presentation.utils.adapter.HeadlinesViewPagerAdapter
import com.jesil.toborowei.newstimes.presentation.utils.hideView
import com.jesil.toborowei.newstimes.presentation.utils.showView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var adapter: HeadlinesViewPagerAdapter


    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = HomeFragmentBinding.bind(view)
        binding.apply {
            homeFragmentViewPagerHeadlines.setPadding(20, 10, 30, 10)
            homeFragmentSeeAllHeadlines.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_home_to_headlinesFragment)
            }

            homeFragmentHeadlinesRetry.setOnClickListener {
                homeViewModel.retryGetTopHeadlines()
                hideErrorGroupForHeadlinesData()
            }
        }
        homeViewModel.topHeadlines.observe(viewLifecycleOwner) {
            when (it) {
                is DataResult.Success -> it.data?.let { headlines -> success(headlines) }
                is DataResult.Loading -> loading()
                is DataResult.Error -> error()
            }
        }
    }

    private fun success(newsResponse: NewsResponse) = with(binding) {
        homeFragmentHeadlinesProgressBar.hideView()
        adapter = HeadlinesViewPagerAdapter(
            newsArticlesItems = newsResponse.articles,
            context = requireContext()
        )
        homeFragmentViewPagerHeadlines.adapter = adapter

    }

    private fun error() = with(binding) {
        homeFragmentHeadlinesProgressBar.hideView()
        errorGroup.showView()
    }

    private fun loading() = with(binding) {
        homeFragmentHeadlinesProgressBar.showView()
    }

    // for retrying
    private fun hideErrorGroupForHeadlinesData() = with(binding) {
        errorGroup.hideView()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}