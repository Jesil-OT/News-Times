package com.jesil.toborowei.newstimes.presentation.fragments.headlines

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.databinding.HeadlinesFragmentBinding
import com.jesil.toborowei.newstimes.presentation.utils.adapter.HeadlinesPagingAdapter
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

        val headlinesPagingAdapter = HeadlinesPagingAdapter()
        binding.apply {
            headlinesFragmentRecyclerView.apply {
                setHasFixedSize(true)
                adapter = headlinesPagingAdapter
            }
        }

        viewModel.headlines.observe(viewLifecycleOwner){
            headlinesPagingAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            Log.d("HeadlinesFragment", "onViewCreated: $it")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}