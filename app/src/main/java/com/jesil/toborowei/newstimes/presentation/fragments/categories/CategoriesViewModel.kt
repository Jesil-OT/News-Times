package com.jesil.toborowei.newstimes.presentation.fragments.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jesil.toborowei.newstimes.presentation.fragments.categories.business.BusinessPager
import com.jesil.toborowei.newstimes.presentation.utils.NewsConstants.NEWS_COUNTRY_HEADLINES
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val businessPager: BusinessPager
) : ViewModel() {

    val businessNews =
        businessPager.getBusinessNews(NEWS_COUNTRY_HEADLINES).cachedIn(viewModelScope)
}