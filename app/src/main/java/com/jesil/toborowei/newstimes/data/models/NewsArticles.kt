package com.jesil.toborowei.newstimes.data.models

import com.google.gson.annotations.SerializedName

data class NewsArticles(
    @SerializedName("source")
    val newsSource: NewsSource? = null,

    @SerializedName("author")
    val newsAuthor: String? = null,

    @SerializedName("title")
    val newsTitle : String? = null,

    @SerializedName("description")
    val newsDescription: String? = null,

    @SerializedName("url")
    val newsUrl: String? = null,

    @SerializedName("urlToImage")
    val newsUrlToImage: String? = null,

    @SerializedName("publishedAt")
    val newsPublishedAt: String? = null,

    @SerializedName("content")
    val newsContent: String? = null
)
