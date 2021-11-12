package com.jesil.toborowei.newstimes.presentation.fragments.categories.health

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.data.models.NewsArticles
import com.jesil.toborowei.newstimes.databinding.HealthFragmentBinding
import com.jesil.toborowei.newstimes.presentation.fragments.categories.CategoriesViewModel
import com.jesil.toborowei.newstimes.presentation.utils.OpenNewsContent
import com.jesil.toborowei.newstimes.presentation.utils.adapter.categories_adapter.CategoriesPagingAdapter
import com.jesil.toborowei.newstimes.presentation.utils.adapter.headlines_adapter.NewsErrorHeaderFooterAdapter
import com.jesil.toborowei.newstimes.presentation.utils.content.NewsContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HealthFragment : Fragment(R.layout.health_fragment), OpenNewsContent {
    private val viewModel by viewModels<CategoriesViewModel>()
    private var _binding : HealthFragmentBinding? = null
    private val binding get() = _binding!!
    private val healthAdapter by lazy{
        CategoriesPagingAdapter(requireContext(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = HealthFragmentBinding.bind(view)
        binding.apply {
            healthRecyclerView.apply {
                setHasFixedSize(true)
                adapter = healthAdapter.withLoadStateHeaderAndFooter(
                    header = NewsErrorHeaderFooterAdapter {
                        healthAdapter.retry()
                    },
                    footer = NewsErrorHeaderFooterAdapter {
                        healthAdapter.retry()
                    }
                )
            }
            healthRetry.setOnClickListener {
                healthAdapter.retry()
            }
        }

        healthAdapter.addLoadStateListener {
            combinedLoadStates(it)
        }

        viewModel.healthNews.observe(viewLifecycleOwner) {
            healthAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }

    private fun combinedLoadStates(combinedLoadStates: CombinedLoadStates) = with(binding){
        healthRecyclerView.isVisible = combinedLoadStates.source.refresh is LoadState.NotLoading
        healthProgressBar.isVisible = combinedLoadStates.source.refresh is LoadState.Loading
        errorGroup.isVisible = combinedLoadStates.source.refresh is LoadState.Error
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun newsContent(newsArticles: NewsArticles) {
        val modalBottomSheet = NewsContent(newsArticles)
        modalBottomSheet.show(parentFragmentManager, NewsContent.TAG)
    }


}