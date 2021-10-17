package com.jesil.toborowei.newstimes.presentation.fragments.home

import androidx.lifecycle.*
import com.jesil.toborowei.newstimes.data.models.NewsResponse
import com.jesil.toborowei.newstimes.domain.repository.NewsRepository
import com.jesil.toborowei.newstimes.presentation.utils.DataResult
import com.jesil.toborowei.newstimes.presentation.utils.NewsConstants.NEWS_API_KEY
import com.jesil.toborowei.newstimes.presentation.utils.NewsConstants.NEWS_COUNTRY_HEADLINES
import com.jesil.toborowei.newstimes.presentation.utils.NewsConstants.NEWS_DOMAINS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _allHomeNews: MutableLiveData<DataResult<Pair<NewsResponse, NewsResponse>>> =
        MutableLiveData()
    val allHomeNews: LiveData<DataResult<Pair<NewsResponse, NewsResponse>>> = _allHomeNews

    init {
        getAllHomeNews()
    }

    private fun getAllHomeNews() {
        viewModelScope.launch {
            combine(
                newsRepository.getTopHeadlines(NEWS_COUNTRY_HEADLINES, NEWS_API_KEY, 1),
                newsRepository.getEverything(NEWS_DOMAINS, NEWS_API_KEY, 1)
            ) { topHeadlines, everything ->
                Pair(topHeadlines, everything)
            }
                .onStart {
                    _allHomeNews.postValue(DataResult.Loading)
                }
                .catch { homeNewsError ->
                    _allHomeNews.postValue(DataResult.Error(error = homeNewsError))
                }
                .collect { homeNewsResult ->
                    _allHomeNews.postValue(DataResult.Success(data = homeNewsResult))
                }
        }
    }

    fun retryAllHomeNews() {
        getAllHomeNews()
    }
}