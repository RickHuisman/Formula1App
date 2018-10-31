package com.rickhuisman.formula1app.ergast;

import com.rickhuisman.formula1app.ergast.models.Feed;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RaceWebService {

    // Race Schedule
    @Headers("Content-Type: application/json")
    @GET("api/f1/2018.json")
    Observable<Feed> getRaceSchedule();

    // Race Results
    @Headers("Content-Type: application/json")
    @GET("api/f1/2018/results/{round}.json")
    Observable<Feed> getRaceResults(@Path("round") String grid);
}