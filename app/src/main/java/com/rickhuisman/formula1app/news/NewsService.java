package com.rickhuisman.formula1app.news;

import com.rickhuisman.formula1app.news.models.RssFeed;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {

    @GET("rss/feed/f1")
    Call<RssFeed> getArticles();
}
