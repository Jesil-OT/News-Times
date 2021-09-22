package com.jesil.toborowei.newstimes.presentation.fragments.headlines

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.databinding.HeadlinesFragmentBinding
import com.jesil.toborowei.newstimes.presentation.utils.adapter.HeadlinesPagingAdapter
import com.jesil.toborowei.newstimes.presentation.utils.adapter.NewsErrorHeaderFooterAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeadlinesFragment : Fragment(R.layout.headlines_fragment) {
    private val viewModel: HeadlinesViewModel by viewModels()
    private var _binding: HeadlinesFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = HeadlinesFragmentBinding.bind(view)

        val headlinesPagingAdapter = HeadlinesPagingAdapter(requireContext())
        binding.apply {
            headlinesRecyclerView.apply {
                setHasFixedSize(true)
                adapter = headlinesPagingAdapter.withLoadStateHeaderAndFooter(
                    header = NewsErrorHeaderFooterAdapter{
                        headlinesPagingAdapter.retry()
                    },
                    footer = NewsErrorHeaderFooterAdapter{
                        headlinesPagingAdapter.retry()
                    }
                )
            }
            headlinesRetry.setOnClickListener {
                headlinesPagingAdapter.retry()
            }
        }

        headlinesPagingAdapter.addLoadStateListener {
            combinedLoadStates(it)
        }

        viewModel.headlines.observe(viewLifecycleOwner) {
            headlinesPagingAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            Log.d("HeadlinesFragment", "onViewCreated: $it")
        }

    }

    private fun combinedLoadStates(combinedLoadStates: CombinedLoadStates) = with(binding) {
        headlinesRecyclerView.isVisible = combinedLoadStates.source.refresh is LoadState.NotLoading
        headlinesErrorGroup.isVisible = combinedLoadStates.source.refresh is LoadState.Error
        headlinesProgressBar.isVisible = combinedLoadStates.source.refresh is LoadState.Loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}