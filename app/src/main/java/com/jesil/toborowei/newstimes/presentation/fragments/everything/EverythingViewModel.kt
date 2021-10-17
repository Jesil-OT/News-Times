package com.jesil.toborowei.newstimes.presentation.fragments.everything

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jesil.toborowei.newstimes.presentation.utils.NewsConstants.NEWS_DOMAINS
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EverythingViewModel @Inject constructor(
    private val everythingPager: EverythingPager
) : ViewModel() {

    val headlines = everythingPager.getEverything(NEWS_DOMAINS).cachedIn(viewModelScope)

}