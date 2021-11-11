package com.jesil.toborowei.newstimes.presentation.fragments.categories.entertainment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.data.models.NewsArticles
import com.jesil.toborowei.newstimes.databinding.EntertainmentFragmentBinding
import com.jesil.toborowei.newstimes.presentation.fragments.categories.CategoriesViewModel
import com.jesil.toborowei.newstimes.presentation.utils.OpenNewsContent
import com.jesil.toborowei.newstimes.presentation.utils.adapter.categories_adapter.CategoriesPagingAdapter
import com.jesil.toborowei.newstimes.presentation.utils.adapter.headlines_adapter.NewsErrorHeaderFooterAdapter
import com.jesil.toborowei.newstimes.presentation.utils.content.NewsContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntertainmentFragment : Fragment(R.layout.entertainment_fragment), OpenNewsContent {
    private val viewModel by viewModels<CategoriesViewModel>()
    private var _binding: EntertainmentFragmentBinding? = null
    private val binding get() = _binding!!
    private val entertainmentAdapter by lazy {
        CategoriesPagingAdapter(requireContext(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = EntertainmentFragmentBinding.bind(view)
        binding.apply {
            entertainmentRecyclerView.apply {
                setHasFixedSize(true)
                adapter = entertainmentAdapter.withLoadStateHeaderAndFooter(
                    header = NewsErrorHeaderFooterAdapter {
                        entertainmentAdapter.retry()
                    },
                    footer = NewsErrorHeaderFooterAdapter {
                        entertainmentAdapter.retry()
                    }
                )
            }
            entertainmentRetry.setOnClickListener {
                entertainmentAdapter.retry()
            }
        }

        entertainmentAdapter.addLoadStateListener {
            combinedLoadStates(it)
        }

        viewModel.entertainmentNews.observe(viewLifecycleOwner) {
            entertainmentAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun combinedLoadStates(combinedLoadStates: CombinedLoadStates) = with(binding){
        entertainmentRecyclerView.isVisible = combinedLoadStates.source.refresh is LoadState.NotLoading
        entertainmentProgressBar.isVisible = combinedLoadStates.source.refresh is LoadState.Loading
        errorGroup.isVisible = combinedLoadStates.source.refresh is LoadState.Error
    }

    override fun newsContent(newsArticles: NewsArticles) {
        val modalBottomSheet = NewsContent(newsArticles)
        modalBottomSheet.show(parentFragmentManager, NewsContent.TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}