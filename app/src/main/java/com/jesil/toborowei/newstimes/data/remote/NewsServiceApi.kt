package com.jesil.toborowei.newstimes.data.remote

import com.jesil.toborowei.newstimes.data.models.NewsResponse
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
}