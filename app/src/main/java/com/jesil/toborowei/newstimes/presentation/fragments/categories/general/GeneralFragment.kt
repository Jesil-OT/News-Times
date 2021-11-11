package com.jesil.toborowei.newstimes.presentation.fragments.categories.general

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
import com.jesil.toborowei.newstimes.databinding.GeneralFragmentBinding
import com.jesil.toborowei.newstimes.presentation.fragments.categories.CategoriesViewModel
import com.jesil.toborowei.newstimes.presentation.utils.OpenNewsContent
import com.jesil.toborowei.newstimes.presentation.utils.adapter.categories_adapter.CategoriesPagingAdapter
import com.jesil.toborowei.newstimes.presentation.utils.adapter.headlines_adapter.NewsErrorHeaderFooterAdapter
import com.jesil.toborowei.newstimes.presentation.utils.content.NewsContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GeneralFragment : Fragment(R.layout.general_fragment), OpenNewsContent{
    private val viewModel by viewModels<CategoriesViewModel>()
    private var _binding: GeneralFragmentBinding? = null
    private val binding get() = _binding!!
    private val generalAdapter by lazy {
        CategoriesPagingAdapter(requireContext(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = GeneralFragmentBinding.bind(view)
        binding.apply {
            generalRecyclerView.apply {
                setHasFixedSize(true)
                adapter = generalAdapter.withLoadStateHeaderAndFooter(
                    header = NewsErrorHeaderFooterAdapter {
                        generalAdapter.retry()
                    },
                    footer = NewsErrorHeaderFooterAdapter {
                        generalAdapter.retry()
                    }
                )
            }
            generalRetry.setOnClickListener {
                generalAdapter.retry()
            }
        }

        generalAdapter.addLoadStateListener {
            combinedLoadStates(it)
        }

        viewModel.generalNews.observe(viewLifecycleOwner) {
            generalAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun combinedLoadStates(combinedLoadStates: CombinedLoadStates) = with(binding){
        generalRecyclerView.isVisible = combinedLoadStates.source.refresh is LoadState.NotLoading
        generalProgressBar.isVisible = combinedLoadStates.source.refresh is LoadState.Loading
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