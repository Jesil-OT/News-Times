package com.jesil.toborowei.newstimes.presentation.fragments.everything

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.jesil.toborowei.newstimes.data.remote.NewsServiceApi
import com.jesil.toborowei.newstimes.domain.repository.EverythingPagingSource
import com.jesil.toborowei.newstimes.domain.repository.HeadlinesPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EverythingPager @Inject constructor(
    private val newsServiceApi: NewsServiceApi
) {

    fun getEverything(domain: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                EverythingPagingSource(
                    newsDomain = domain,
                    newsServiceApi = newsServiceApi,
                )
            }
        ).liveData
}