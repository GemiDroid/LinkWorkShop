package com.gemi_droid.linkdev_workshop.model.network.manager;

import com.gemi_droid.linkdev_workshop.model.network.models.NewsResponse;
import com.gemi_droid.linkdev_workshop.model.network.retrofit.RetrofitRepository;
import com.gemi_droid.linkdev_workshop.model.network.webservice.API;
import retrofit2.Call;


public class NewsManager {

    RetrofitRepository retrofitRepository;

    public NewsManager() {
        retrofitRepository = new RetrofitRepository();
    }

    public Call<NewsResponse> News() {
        API Api_Service = retrofitRepository.getRetrofit().create(API.class);
       return Api_Service.GetNews(RetrofitRepository.NEWS_SOURCE,RetrofitRepository.API_KEY);
    }

}
