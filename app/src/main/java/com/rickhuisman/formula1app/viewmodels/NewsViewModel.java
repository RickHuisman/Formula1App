package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;

import com.rickhuisman.formula1app.news.NewsRepository;
import com.rickhuisman.formula1app.news.models.RssFeed;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import retrofit2.Call;

public class NewsViewModel extends AndroidViewModel {

    private NewsRepository mNewsRepository;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        mNewsRepository = new NewsRepository();
    }

    public Call<RssFeed> getArticles() {
        return mNewsRepository.getArticles();
    }
}
