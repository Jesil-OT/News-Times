package com.jesil.toborowei.newstimes.presentation.fragments.categories.science

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.data.models.NewsArticles
import com.jesil.toborowei.newstimes.databinding.ScienceFragmentBinding
import com.jesil.toborowei.newstimes.presentation.fragments.categories.CategoriesViewModel
import com.jesil.toborowei.newstimes.presentation.utils.OpenNewsContent
import com.jesil.toborowei.newstimes.presentation.utils.adapter.categories_adapter.CategoriesPagingAdapter
import com.jesil.toborowei.newstimes.presentation.utils.adapter.headlines_adapter.NewsErrorHeaderFooterAdapter
import com.jesil.toborowei.newstimes.presentation.utils.content.NewsContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScienceFragment : Fragment(R.layout.science_fragment), OpenNewsContent {
    private val viewModel by viewModels<CategoriesViewModel>()
    private var _binding: ScienceFragmentBinding? = null
    private val binding get() = _binding!!
    private val scienceAdapter by lazy {
        CategoriesPagingAdapter(requireContext(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ScienceFragmentBinding.bind(view)
        binding.apply {
            scienceRecyclerView.apply {
                setHasFixedSize(true)
                adapter = scienceAdapter.withLoadStateHeaderAndFooter(
                    header = NewsErrorHeaderFooterAdapter {
                        scienceAdapter.retry()
                    },
                    footer = NewsErrorHeaderFooterAdapter {
                        scienceAdapter.retry()
                    }
                )
            }
            scienceRetry.setOnClickListener {
                scienceAdapter.retry()
            }
        }

        scienceAdapter.addLoadStateListener {
            combinedLoadStates(it)
        }

        viewModel.scienceNews.observe(viewLifecycleOwner) {
            scienceAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun combinedLoadStates(combinedLoadStates: CombinedLoadStates) = with(binding) {
        scienceRecyclerView.isVisible = combinedLoadStates.source.refresh is LoadState.NotLoading
        scienceProgressBar.isVisible = combinedLoadStates.source.refresh is LoadState.Loading
        errorGroup.isVisible = combinedLoadStates.source.refresh is LoadState.Error
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun newsContent(newsArticles: NewsArticles) {
        val modalBottomSheet = NewsContent(newsArticles)
        modalBottomSheet.show(parentFragmentManager, NewsContent.TAG)
    }

}