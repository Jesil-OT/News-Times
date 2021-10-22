package com.jesil.toborowei.newstimes.presentation.fragments.categories.business

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.data.models.NewsArticles
import com.jesil.toborowei.newstimes.databinding.BusinessFragmentBinding
import com.jesil.toborowei.newstimes.presentation.fragments.categories.CategoriesViewModel
import com.jesil.toborowei.newstimes.presentation.utils.DataResult
import com.jesil.toborowei.newstimes.presentation.utils.OpenNewsContent
import com.jesil.toborowei.newstimes.presentation.utils.adapter.categories_adapter.CategoriesPagingAdapter
import com.jesil.toborowei.newstimes.presentation.utils.adapter.headlines_adapter.NewsErrorHeaderFooterAdapter
import com.jesil.toborowei.newstimes.presentation.utils.content.NewsContent
import com.jesil.toborowei.newstimes.presentation.utils.hideView
import com.jesil.toborowei.newstimes.presentation.utils.showView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusinessFragment : Fragment(R.layout.business_fragment), OpenNewsContent {
    private var _binding: BusinessFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CategoriesViewModel>()
    private val businessAdapter by lazy {
        CategoriesPagingAdapter(requireContext(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = BusinessFragmentBinding.bind(view)
        binding.apply {
            businessRecyclerView.apply {
                setHasFixedSize(true)
                adapter = businessAdapter.withLoadStateHeaderAndFooter(
                    header = NewsErrorHeaderFooterAdapter {
                        businessAdapter.retry()
                    },
                    footer = NewsErrorHeaderFooterAdapter {
                        businessAdapter.retry()
                    }
                )
            }
            businessRetry.setOnClickListener {
                businessAdapter.retry()
            }
        }

        businessAdapter.addLoadStateListener {
            combinedLoadStates(it)
        }

        viewModel.businessNews.observe(viewLifecycleOwner) {
            businessAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun combinedLoadStates(combinedLoadStates: CombinedLoadStates) = with(binding) {
        businessRecyclerView.isVisible = combinedLoadStates.source.refresh is LoadState.NotLoading
        errorGroup.isVisible = combinedLoadStates.source.refresh is LoadState.Error
        businessProgressBar.isVisible = combinedLoadStates.source.refresh is LoadState.Loading
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