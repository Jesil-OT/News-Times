package com.jesil.toborowei.newstimes.presentation.fragments.categories.sports

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.data.models.NewsArticles
import com.jesil.toborowei.newstimes.databinding.SportsFragmentBinding
import com.jesil.toborowei.newstimes.presentation.fragments.categories.CategoriesViewModel
import com.jesil.toborowei.newstimes.presentation.utils.OpenNewsContent
import com.jesil.toborowei.newstimes.presentation.utils.adapter.categories_adapter.CategoriesPagingAdapter
import com.jesil.toborowei.newstimes.presentation.utils.adapter.headlines_adapter.NewsErrorHeaderFooterAdapter
import com.jesil.toborowei.newstimes.presentation.utils.content.NewsContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SportsFragment : Fragment(R.layout.sports_fragment), OpenNewsContent {
    private val viewModel by viewModels<CategoriesViewModel>()
    private var _binding: SportsFragmentBinding? = null
    private val binding get() = _binding!!
    private val sportAdapter by lazy {
        CategoriesPagingAdapter(requireContext(), this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SportsFragmentBinding.bind(view)
        binding.apply {
            with(sportsRecyclerView){
                setHasFixedSize(true)
                adapter = sportAdapter.withLoadStateHeaderAndFooter(
                    header = NewsErrorHeaderFooterAdapter{
                        sportAdapter.retry()
                    },
                    footer = NewsErrorHeaderFooterAdapter{
                        sportAdapter.retry()
                    }
                )
            }
            sportsRetry.setOnClickListener {
                sportAdapter.retry()
            }
        }

        sportAdapter.addLoadStateListener {
            combinedLoadStates(it)
        }

        viewModel.sportsNews.observe(viewLifecycleOwner) {
            sportAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun combinedLoadStates(combinedLoadStates: CombinedLoadStates) = with(binding){
        sportsRecyclerView.isVisible = combinedLoadStates.source.refresh is LoadState.NotLoading
        sportsProgressBar.isVisible = combinedLoadStates.source.refresh is LoadState.Loading
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