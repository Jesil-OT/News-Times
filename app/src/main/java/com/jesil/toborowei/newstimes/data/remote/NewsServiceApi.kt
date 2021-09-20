package com.jesil.toborowei.newstimes.data.remote

import com.jesil.toborowei.newstimes.data.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServiceApi{

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country : String = "us",
        @Query("apiKey") apiKey: String
    ) : NewsResponse
}