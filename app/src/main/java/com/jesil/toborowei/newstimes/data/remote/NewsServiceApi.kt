package com.jesil.toborowei.newstimes.data.remote

import com.jesil.toborowei.newstimes.data.models.NewsResponse
import com.jesil.toborowei.newstimes.presentation.utils.NewsConstants.NEWS_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServiceApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int
    ): NewsResponse


    @GET("everything")
    suspend fun getEverything(
        @Query("domains") domains: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String = "en"
    ): NewsResponse


    @GET("top-headlines")
    suspend fun getCategoriesNews(
        @Query("country") country: String = "us",
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = NEWS_API_KEY,
        @Query("page") page: Int
    ): NewsResponse
}