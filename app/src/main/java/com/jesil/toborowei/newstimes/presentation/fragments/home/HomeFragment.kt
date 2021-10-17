package com.jesil.toborowei.newstimes.presentation.fragments.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.data.models.NewsResponse
import com.jesil.toborowei.newstimes.databinding.HomeFragmentBinding
import com.jesil.toborowei.newstimes.presentation.utils.DataResult
import com.jesil.toborowei.newstimes.presentation.utils.adapter.everything_adapter.EverythingViewPagerAdapter
import com.jesil.toborowei.newstimes.presentation.utils.adapter.headlines_adapter.HeadlinesViewPagerAdapter
import com.jesil.toborowei.newstimes.presentation.utils.hideView
import com.jesil.toborowei.newstimes.presentation.utils.showView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var headlinesViewPagerAdapter: HeadlinesViewPagerAdapter
    private lateinit var everythingViewPagerAdapter: EverythingViewPagerAdapter


    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = HomeFragmentBinding.bind(view)
        binding.apply {
            homeFragmentViewPagerHeadlines.setPadding(30, 10, 30, 10)
            homeFragmentViewPagerEverything.setPadding(30, 20, 90, 10)

            homeFragmentSeeAllHeadlines.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_home_to_headlinesFragment)
            }
            homeFragmentSeeAllEverything.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_home_to_everythingFragment)
            }

            homeFragmentHeadlinesRetry.setOnClickListener {
                homeViewModel.retryAllHomeNews()
                hideErrorGroupForHeadlinesData()
            }
        }

        homeViewModel.allHomeNews.observe(viewLifecycleOwner) {newsResponse ->
            when (newsResponse) {
                is DataResult.Success -> newsResponse.data?.first?.let { topHeadlines ->
                    success(
                        topHeadlines,
                        newsResponse.data.second
                    )
                }
                is DataResult.Loading -> loading()
                is DataResult.Error -> error()
            }
        }
    }

    private fun success(
        topHeadlinesNewsResponse: NewsResponse,
        everythingNewsResponse: NewsResponse
    ) {
        topHeadlinesSuccess(topHeadlinesNewsResponse)
        everythingSuccess(everythingNewsResponse)
    }

    private fun error() = with(binding) {
        homeFragmentHeadlinesProgressBar.hideView()
        errorGroup.showView()
    }

    private fun loading() = with(binding) {
        homeFragmentHeadlinesProgressBar.showView()
    }

    private fun topHeadlinesSuccess(newsResponse: NewsResponse) = with(binding) {
        homeFragmentHeadlinesProgressBar.hideView()
        dummyTextViewGroup.showView()
        headlinesViewPagerAdapter = HeadlinesViewPagerAdapter(
            newsArticlesItems = newsResponse.articles,
            context = requireContext()
        )
        homeFragmentViewPagerHeadlines.adapter = headlinesViewPagerAdapter
    }

    private fun everythingSuccess(newsResponse: NewsResponse) = with(binding) {
        homeFragmentHeadlinesProgressBar.hideView()
        everythingViewPagerAdapter = EverythingViewPagerAdapter(
            newsArticlesItems = newsResponse.articles,
            context = requireContext()
        )
        homeFragmentViewPagerEverything.adapter = everythingViewPagerAdapter
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