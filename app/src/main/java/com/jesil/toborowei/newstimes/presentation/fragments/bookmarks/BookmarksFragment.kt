package com.jesil.toborowei.newstimes.presentation.fragments.bookmarks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.databinding.BookmarksFragmentBinding

class BookmarksFragment: Fragment(R.layout.bookmarks_fragment) {
    private val bookmarksViewModel: BookmarksViewModel by viewModels()
    private var _binding: BookmarksFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = BookmarksFragmentBinding.bind(view)
        binding.apply {
            //   TODO("Do something inside here")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}