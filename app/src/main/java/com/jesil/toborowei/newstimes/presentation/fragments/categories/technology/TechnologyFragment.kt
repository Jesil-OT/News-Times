package com.jesil.toborowei.newstimes.presentation.fragments.categories.technology

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jesil.toborowei.newstimes.R

class TechnologyFragment : Fragment() {

    companion object {
        fun newInstance() = TechnologyFragment()
    }

    private lateinit var viewModel: TechnologyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.technology_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TechnologyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}