package com.gemi_droid.linkdev_workshop.model.network.webservice

import com.gemi_droid.linkdev_workshop.model.network.models.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    /*                      # API for fetching News List                                          */

    @GET("v1/articles")
    fun GetNews(
            @Query("source") Sourcename: String,
            @Query("apiKey") ApiKey: String): Call<NewsResponse>

}
