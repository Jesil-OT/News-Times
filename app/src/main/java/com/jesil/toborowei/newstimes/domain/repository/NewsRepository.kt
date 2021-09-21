package com.jesil.toborowei.newstimes.domain.repository

import com.jesil.toborowei.newstimes.data.models.NewsResponse
import com.jesil.toborowei.newstimes.data.remote.NewsServiceApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsServiceApi: NewsServiceApi
) {
    suspend fun getTopHeadlines(country: String, apiKey: String, page: Int): Flow<NewsResponse> = flow {
        val topHeadlines = newsServiceApi.getTopHeadlines(country = country, apiKey = apiKey, page = page)
        delay(1000)
        emit(topHeadlines)

    }
}