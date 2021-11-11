package com.jesil.toborowei.newstimes.presentation.fragments.categories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.jesil.toborowei.newstimes.data.remote.NewsServiceApi
import com.jesil.toborowei.newstimes.domain.repository.CategoriesPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesPager @Inject constructor(
    private val newsServiceApi: NewsServiceApi
) {
    fun getCategoriesNews(newCategory: String, country: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CategoriesPagingSource(
                    newsServiceApi = newsServiceApi,
                    category = newCategory,
                    country = country
                )
            }
        ).liveData
}