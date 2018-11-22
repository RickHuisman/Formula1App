package com.rickhuisman.formula1app.ergast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.rickhuisman.formula1app.ergast.models.Feed;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ErgastRepository {
    private static final String BASE_URL = "https://ergast.com/";

    private RaceWebService raceApi;

    public ErgastRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        raceApi = retrofit.create(RaceWebService.class);
    }

    public Observable<Feed> getRaceSchedule() {
        return raceApi.getRaceSchedule();
    }

    public Observable<Feed> getRaceResults() {
        return raceApi.getRaceResults();
    }
}