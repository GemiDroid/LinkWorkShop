package com.gemi_droid.linkdev_workshop.view_model;

import com.gemi_droid.linkdev_workshop.model.network.models.NewsModel;
import com.gemi_droid.linkdev_workshop.model.repository.NewsRepository;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


public class NewsViewModel extends ViewModel {

    private static LiveData<List<NewsModel>> NewsList = new LiveData<List<NewsModel>>(){};

    public void getNews() {
        NewsList = NewsRepository.Companion.getInstance().getNews();
    }

    public LiveData<List<NewsModel>> observeOnNews(){
        return NewsList;
    }
}
