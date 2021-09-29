package com.jesil.toborowei.newstimes.presentation.fragments.everything

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.databinding.EverythingFragmentBinding
import com.jesil.toborowei.newstimes.presentation.utils.adapter.everything_adapter.EverythingPagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EverythingFragment : Fragment(R.layout.everything_fragment) {

    private val everythingViewModel: EverythingViewModel by viewModels()
    private var _binding : EverythingFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = EverythingFragmentBinding.bind(view)
        val everythingPagingAdapter = EverythingPagingAdapter(requireContext())
        binding.apply {
            everythingRecyclerView.apply {
                setHasFixedSize(true)
                adapter = everythingPagingAdapter
            }
            everythingRetry.setOnClickListener {
                everythingPagingAdapter.retry()
            }
        }

        everythingPagingAdapter.addLoadStateListener {
            combinedLoadStates(it)
        }

        everythingViewModel.headlines.observe(viewLifecycleOwner) {
            everythingPagingAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            Log.d("HeadlinesFragment", "onViewCreated: $it")
        }

    }

    private fun combinedLoadStates(combinedLoadStates: CombinedLoadStates) = with(binding) {
        everythingRecyclerView.isVisible = combinedLoadStates.source.refresh is LoadState.NotLoading
        everythingErrorGroup.isVisible = combinedLoadStates.source.refresh is LoadState.Error
        everythingProgressBar.isVisible = combinedLoadStates.source.refresh is LoadState.Loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}