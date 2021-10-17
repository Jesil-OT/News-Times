package com.jesil.toborowei.newstimes.presentation.fragments.categories.business

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesil.toborowei.newstimes.data.models.NewsResponse
import com.jesil.toborowei.newstimes.domain.repository.NewsRepository
import com.jesil.toborowei.newstimes.presentation.utils.DataResult
import com.jesil.toborowei.newstimes.presentation.utils.NewsConstants
import com.jesil.toborowei.newstimes.presentation.utils.NewsConstants.NEWS_API_KEY
import com.jesil.toborowei.newstimes.presentation.utils.NewsConstants.NEWS_COUNTRY_HEADLINES
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusinessViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _businessNews = MutableLiveData<DataResult<NewsResponse>>()
    val businessNews = _businessNews

    init {
        getAllBusinessNews()
    }

    private fun getAllBusinessNews() {
        viewModelScope.launch {
            repository.getCategoriesNews(NEWS_COUNTRY_HEADLINES, "business", NEWS_API_KEY)
                .onStart {
                    _businessNews.postValue(DataResult.Loading)
                }
                .catch {
                    _businessNews.postValue(DataResult.Error(it))
                }
                .collect {
                    _businessNews.postValue(DataResult.Success(it))
                }
        }
    }

    fun retryBusinessNews() {
        getAllBusinessNews()
    }
}