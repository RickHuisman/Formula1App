package com.rickhuisman.formula1app.ergast;

import com.rickhuisman.formula1app.ergast.models.Feed;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ErgastWebService {

    @Headers("Content-Type: application/json")
    @GET("api/f1/{season}/schedule.json")
    Observable<Feed> getRaceSchedule(@Path("season") int season);
}