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

    @Headers("Content-Type: application/json")
    @GET("api/f1/{season}/{round}/info.json")
    Observable<Feed> getRaceInfo(@Path("season") int season, @Path("round") int round);

    @Headers("Content-Type: application/json")
    @GET("api/f1/{season}/{round}/qualifying.json")
    Observable<Feed> getQualifyingResults(@Path("season") int season, @Path("round") int round);

    @Headers("Content-Type: application/json")
    @GET("api/f1/{season}/{round}/results.json")
    Observable<Feed> getRaceResults(@Path("season") int season, @Path("round") int round);

    @Headers("Content-Type: application/json")
    @GET("api/f1/current/driverstandings.json")
    Observable<Feed> getDriverStandings();

    @Headers("Content-Type: application/json")
    @GET("api/f1/current/constructorstandings.json")
    Observable<Feed> getConstructorStandings();

    @Headers("Content-Type: application/json")
    @GET("api/f1/driverinfo/{driver}.json")
    Observable<Feed> getDriverInfo(@Path("driver") String driver);

    @Headers("Content-Type: application/json")
    @GET("api/f1/constructorinfo/{constructor}.json")
    Observable<Feed> getConstructorInfo(@Path("constructor") String constructor);
}