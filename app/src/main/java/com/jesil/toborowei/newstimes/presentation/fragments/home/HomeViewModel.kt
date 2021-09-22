package com.jesil.toborowei.newstimes.presentation.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesil.toborowei.newstimes.data.models.NewsArticles
import com.jesil.toborowei.newstimes.data.models.NewsResponse
import com.jesil.toborowei.newstimes.domain.repository.NewsRepository
import com.jesil.toborowei.newstimes.presentation.utils.DataResult
import com.jesil.toborowei.newstimes.presentation.utils.NewsConstants.NEWS_API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _topHeadlines: MutableLiveData<DataResult<NewsResponse>> = MutableLiveData()
    val topHeadlines: LiveData<DataResult<NewsResponse>> = _topHeadlines

    init {
        getTopHeadlines()
    }

    private fun getTopHeadlines() {
       topHeadlines()
    }

    fun retryGetTopHeadlines() {
        topHeadlines()
    }

    private fun topHeadlines(){
        viewModelScope.launch {
            newsRepository.getTopHeadlines(country = "us", apiKey = NEWS_API_KEY, 1)
                .onStart {
                    _topHeadlines.postValue(DataResult.Loading)
                }
                .catch { error ->
                    _topHeadlines.postValue(DataResult.Error(error = error))
                }
                .collect {
                    _topHeadlines.postValue(DataResult.Success(data = it))
                }
        }
    }
}