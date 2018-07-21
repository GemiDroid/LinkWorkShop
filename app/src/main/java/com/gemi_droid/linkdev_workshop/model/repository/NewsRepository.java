package com.gemi_droid.linkdev_workshop.model.repository;

import android.util.Log;

import com.gemi_droid.linkdev_workshop.model.network.manager.NewsManager;
import com.gemi_droid.linkdev_workshop.model.network.models.NewsModel;
import com.gemi_droid.linkdev_workshop.model.network.models.NewsResponse;
import com.gemi_droid.linkdev_workshop.model.network.retrofit.RetrofitRepository;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private NewsManager newsManager;

    public static NewsRepository getInstance() {
        return new NewsRepository();
    }

    public MutableLiveData<List<NewsModel>> LiveNewsList = new MutableLiveData<List<NewsModel>>() {
    };

    public LiveData<List<NewsModel>> getNews() {
        newsManager = new NewsManager();
        newsManager.News().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.code() >= RetrofitRepository.CODE_OK_200 && response.code() < RetrofitRepository.CODE_OK_300) {
                    if (response.body() != null) {
                        if (response.body().getNewsResutlsList().size() > 0) {
                            LiveNewsList.setValue(response.body().getNewsResutlsList());
                            Log.d("ListSize", "onResponse: " + LiveNewsList.getValue().size());
                        } else {
                            LiveNewsList.postValue(null);
                        }
                    }
                } else {
                    LiveNewsList.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                LiveNewsList.setValue(null);
                t.printStackTrace();
            }
        });
        return LiveNewsList;
    }


}
