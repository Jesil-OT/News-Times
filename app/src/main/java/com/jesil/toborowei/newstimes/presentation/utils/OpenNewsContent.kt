package com.jesil.toborowei.newstimes.presentation.utils

import com.jesil.toborowei.newstimes.data.models.NewsArticles

interface OpenNewsContent {
    fun newsContent(newsArticles: NewsArticles)
}