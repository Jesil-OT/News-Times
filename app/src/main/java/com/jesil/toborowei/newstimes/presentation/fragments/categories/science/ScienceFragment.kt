package com.jesil.toborowei.newstimes.presentation.fragments.categories.science

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jesil.toborowei.newstimes.R

class ScienceFragment : Fragment() {

    companion object {
        fun newInstance() = ScienceFragment()
    }

    private lateinit var viewModel: ScienceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.science_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ScienceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}