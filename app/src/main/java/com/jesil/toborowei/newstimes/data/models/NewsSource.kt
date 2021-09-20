package com.jesil.toborowei.newstimes.data.models

import com.google.gson.annotations.SerializedName

data class NewsSource(
    @SerializedName("id")
    val newsId : String? = null,

    @SerializedName("name")
    val newsName: String? = null
)
