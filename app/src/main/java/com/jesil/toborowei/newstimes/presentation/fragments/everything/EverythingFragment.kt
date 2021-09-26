package com.jesil.toborowei.newstimes.presentation.fragments.everything

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jesil.toborowei.newstimes.R

class EverythingFragment : Fragment() {

    companion object {
        fun newInstance() = EverythingFragment()
    }

    private lateinit var viewModel: EverythingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.everything_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EverythingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}