package com.rickhuisman.formula1app.ergast;

import com.rickhuisman.formula1app.ergast.models.Feed;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ErgastWebService {

    // Race Schedule
    @Headers("Content-Type: application/json")
    @GET("api/f1/2018.json")
    Observable<Feed> getRaceSchedule();

    // Race Results
    @Headers("Content-Type: application/json")
    @GET("api/f1/2018/results.json?limit=500")
    Observable<Feed> getRaceResults();

    // Race Results
    @Headers("Content-Type: application/json")
    @GET("api/f1/2018/{round}/results.json?limit=500")
    Observable<Feed> getRaceResultsTest(@Path("round") int round); // TODO change

    // Qualifying Results
    @Headers("Content-Type: application/json")
    @GET("api/f1/2018/{round}/qualifying.json")
    Observable<Feed> getQualifyingResults(@Path("round") int round);
}