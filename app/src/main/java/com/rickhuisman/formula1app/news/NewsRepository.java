package com.rickhuisman.formula1app.news;

import com.rickhuisman.formula1app.news.models.RssFeed;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class NewsRepository {

    private static final String BASE_URL = "https://www.autosport.com/";
    private NewsService mService;

    public NewsRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        mService = retrofit.create(NewsService.class);
    }

    public Call<RssFeed> getArticles() {
        return mService.getArticles();
    }
}
