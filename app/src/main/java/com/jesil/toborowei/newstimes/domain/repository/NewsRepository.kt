package com.jesil.toborowei.newstimes.domain.repository

import android.util.Log
import com.jesil.toborowei.newstimes.data.models.NewsResponse
import com.jesil.toborowei.newstimes.data.remote.NewsServiceApi
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsServiceApi: NewsServiceApi
) {

    suspend fun getTopHeadlines(country: String, apiKey: String, page: Int) =
        flow {
            val topHeadlines = newsServiceApi.getTopHeadlines(country, apiKey, page)
            delay(1000)
            emit(topHeadlines)
        }

    suspend fun getEverything(
        domains: String,
        apiKey: String,
        page: Int,
    ) = flow {
        val everything = newsServiceApi.getEverything(domains, apiKey, page)
        delay(1000)
        emit(everything)
    }


}