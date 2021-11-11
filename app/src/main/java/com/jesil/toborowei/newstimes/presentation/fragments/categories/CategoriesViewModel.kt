package com.jesil.toborowei.newstimes.presentation.fragments.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jesil.toborowei.newstimes.presentation.utils.NewsConstants.NEWS_COUNTRY_HEADLINES
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesPager: CategoriesPager
) : ViewModel() {

    val businessNews =
        categoriesPager.getCategoriesNews("business", NEWS_COUNTRY_HEADLINES)
            .cachedIn(viewModelScope)

    val entertainmentNews =
        categoriesPager.getCategoriesNews("entertainment", NEWS_COUNTRY_HEADLINES)
            .cachedIn(viewModelScope)

    val generalNews =
        categoriesPager.getCategoriesNews("general", NEWS_COUNTRY_HEADLINES)
            .cachedIn(viewModelScope)

    val healthNews =
        categoriesPager.getCategoriesNews("health", NEWS_COUNTRY_HEADLINES)
            .cachedIn(viewModelScope)

    val scienceNews =
        categoriesPager.getCategoriesNews("science", NEWS_COUNTRY_HEADLINES)
            .cachedIn(viewModelScope)

    val sportsNews =
        categoriesPager.getCategoriesNews("sports", NEWS_COUNTRY_HEADLINES)
            .cachedIn(viewModelScope)

    val technologyNews =
        categoriesPager.getCategoriesNews("technology", NEWS_COUNTRY_HEADLINES)
            .cachedIn(viewModelScope)
}