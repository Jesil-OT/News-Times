package com.jesil.toborowei.newstimes.presentation.fragments.categories.business

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.databinding.BusinessFragmentBinding
import com.jesil.toborowei.newstimes.presentation.utils.DataResult
import com.jesil.toborowei.newstimes.presentation.utils.adapter.categories_adapter.CategoriesRecyclerAdapter
import com.jesil.toborowei.newstimes.presentation.utils.hideView
import com.jesil.toborowei.newstimes.presentation.utils.showView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusinessFragment : Fragment(R.layout.business_fragment) {
    private var _binding: BusinessFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<BusinessViewModel>()
    private val businessAdapter by lazy {
        CategoriesRecyclerAdapter(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = BusinessFragmentBinding.bind(view)
        binding.apply {
            businessRecyclerView.adapter = businessAdapter
            businessRetry.setOnClickListener {
                viewModel.retryBusinessNews()
                hideError()
            }
        }


        viewModel.businessNews.observe(viewLifecycleOwner) {
            when (it) {
                is DataResult.Success -> {
                    businessAdapter.submitList(it.data?.articles)
                    hideProgressBar()
                    hideError()
                }
                is DataResult.Error -> {
                    showError()
                    hideProgressBar()
                }
                is DataResult.Loading -> {
                    hideError()
                    showProgressBar()
                }
            }
        }
    }

    private fun showProgressBar() = with(binding) {
        businessFragmentProgressBar.showView()
    }

    private fun hideProgressBar() = with(binding) {
        businessFragmentProgressBar.hideView()
    }

    private fun hideError() = with(binding) {
        errorGroup.hideView()
    }

    private fun showError() = with(binding) {
        errorGroup.showView()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}