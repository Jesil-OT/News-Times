package com.jesil.toborowei.newstimes.presentation.fragments.headlines

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.databinding.HeadlinesFragmentBinding

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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}