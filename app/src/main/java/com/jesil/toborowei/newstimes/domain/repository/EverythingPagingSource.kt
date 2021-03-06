package com.jesil.toborowei.newstimes.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jesil.toborowei.newstimes.data.models.NewsArticles
import com.jesil.toborowei.newstimes.data.remote.NewsServiceApi
import com.jesil.toborowei.newstimes.presentation.utils.NewsConstants.NEWS_API_KEY
import retrofit2.HttpException
import java.io.IOException

class EverythingPagingSource(
    private val newsServiceApi: NewsServiceApi,
    private val newsDomain : String
): PagingSource<Int, NewsArticles>() {
    private var count = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsArticles> {

        return try {
            val position = params.key ?: 1

            val everythingResponse = newsServiceApi.getEverything(
                domains = newsDomain,
                apiKey = NEWS_API_KEY,
                page = position
            )
            count += everythingResponse.articles.size
            LoadResult.Page(
                data = everythingResponse.articles,
                prevKey = null,
                nextKey = if (count < everythingResponse.totalResults) position + 1 else null
            )
        }
        catch (exception: IOException){
            LoadResult.Error(
                exception
            )
        }
        catch (httpException: HttpException){
            LoadResult.Error(
                httpException
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsArticles>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}