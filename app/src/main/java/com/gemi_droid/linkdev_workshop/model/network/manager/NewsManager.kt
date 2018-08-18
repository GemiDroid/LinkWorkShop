package com.gemi_droid.linkdev_workshop.model.network.manager

import com.gemi_droid.linkdev_workshop.model.network.models.NewsResponse
import com.gemi_droid.linkdev_workshop.model.network.retrofit.RetrofitRepository
import com.gemi_droid.linkdev_workshop.model.network.webservice.API
import retrofit2.Call


class NewsManager {

    internal var retrofitRepository: RetrofitRepository

    init {
        retrofitRepository = RetrofitRepository()
    }

    fun News(): Call<NewsResponse> {
        val Api_Service = retrofitRepository.retrofitInstance().create(API::class.java)
        return Api_Service!!.GetNews(RetrofitRepository.NEWS_SOURCE, RetrofitRepository.API_KEY)
    }

}
