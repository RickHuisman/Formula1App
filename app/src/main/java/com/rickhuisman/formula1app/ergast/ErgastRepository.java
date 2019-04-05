package com.rickhuisman.formula1app.ergast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.rickhuisman.formula1app.ergast.models.Feed;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ErgastRepository {

    private static final String BASE_URL = "http://188.166.27.87:8000/";
    private ErgastWebService mErgastApi;

    public ErgastRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mErgastApi = retrofit.create(ErgastWebService.class);
    }

    public Observable<Feed> getRaceSchedule(int season) {
        return mErgastApi.getRaceSchedule(season);
    }

    public Observable<Feed> getRaceInfo(int season, int round) {
        return mErgastApi.getRaceInfo(season, round);
    }

    public Observable<Feed> getQualifyingResults(int season, int round) {
        return mErgastApi.getQualifyingResults(season, round);
    }

    public Observable<Feed> getRaceResults(int season, int round) {
        return mErgastApi.getRaceResults(season, round);
    }

    public Observable<Feed> getDriverStandings() {
        return mErgastApi.getDriverStandings();
    }

    public Observable<Feed> getConstructorStandings() {
        return mErgastApi.getConstructorStandings();
    }

    public Observable<Feed> getDriverInfo(String driver) {
        return mErgastApi.getDriverInfo(driver);
    }

    public Observable<Feed> getConstructorInfo(String constructor) {
        return mErgastApi.getConstructorInfo(constructor);
    }
}
