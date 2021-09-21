package com.jesil.toborowei.newstimes.presentation.fragments.headlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeadlinesViewModel @Inject constructor(
    private val headlinesPager: HeadlinesPager
) : ViewModel() {

    val headlines = headlinesPager.getTopHeadlines("us").cachedIn(viewModelScope)

}