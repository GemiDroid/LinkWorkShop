package com.gemi_droid.linkdev_workshop.model.repository

import android.util.Log

import com.gemi_droid.linkdev_workshop.model.network.manager.NewsManager
import com.gemi_droid.linkdev_workshop.model.network.models.NewsModel
import com.gemi_droid.linkdev_workshop.model.network.models.NewsResponse
import com.gemi_droid.linkdev_workshop.model.network.retrofit.RetrofitRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {

    private var newsManager: NewsManager? = null

    var LiveNewsList: MutableLiveData<List<NewsModel>> = object : MutableLiveData<List<NewsModel>>() {

    }

    val news: LiveData<List<NewsModel>>
        get() {
            newsManager = NewsManager()
            newsManager!!.News().enqueue(object : Callback<NewsResponse> {
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    if (response.code() >= RetrofitRepository.CODE_OK_200 && response.code() < RetrofitRepository.CODE_OK_300) {
                        if (response.body() != null) {
                            if (response.body().NewsResutlsList.size > 0) {
                                LiveNewsList.setValue(response.body().NewsResutlsList)
                                Log.d("ListSize", "onResponse: " + LiveNewsList.value!!.size)
                            } else {
                                LiveNewsList.setValue(null)
                            }
                        }
                    } else {
                        LiveNewsList.setValue(null)
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    LiveNewsList.value = null
                    t.printStackTrace()
                }
            })
            return LiveNewsList
        }

    companion object {

        val instance: NewsRepository
            get() = NewsRepository()
    }


}
