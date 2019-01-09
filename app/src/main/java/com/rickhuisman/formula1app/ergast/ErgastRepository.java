package com.rickhuisman.formula1app.ergast;

import android.app.Application;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.rickhuisman.formula1app.ergast.models.Feed;
import com.rickhuisman.formula1app.ergast.test.db.Formula1Database;
import com.rickhuisman.formula1app.ergast.test.db.dao.Formula1Dao;
import com.rickhuisman.formula1app.ergast.test.db.entities.DriverStanding;
import com.rickhuisman.formula1app.ergast.test.db.entities.DriverStandingsWithDriver;
import com.rickhuisman.formula1app.ergast.test.db.entities.QualifyingWithDriver;
import com.rickhuisman.formula1app.ergast.test.db.entities.RaceResultWithDriver;
import com.rickhuisman.formula1app.ergast.test.db.entities.RaceWithWinner;

import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ErgastRepository {
    private static final String BASE_URL = "https://ergast.com/";
    private ErgastWebService ergastApi;
    private Formula1Dao formula1Dao;

    public ErgastRepository(Application application) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ergastApi = retrofit.create(ErgastWebService.class);

        Formula1Database db = Formula1Database.getInstance(application);
        formula1Dao = db.noteDao();
    }

    public LiveData<List<RaceWithWinner>> getRacesAndWinners(int year) {
        return formula1Dao.getRacesWithWinners(year);
    }

    public LiveData<List<RaceResultWithDriver>> getRaceResultWithDriver(int raceId) {
        return formula1Dao.getRaceResultWithDriver(raceId);
    }

    public LiveData<List<QualifyingWithDriver>> getQualifyingWithDriver(int raceId) {
        return formula1Dao.getQualifyingWithDriver(raceId);
    }

    public LiveData<List<DriverStandingsWithDriver>> getDriverStandingsWithDriver(int raceId) {
        return formula1Dao.getDriverStandingsWithDriver(raceId);
    }

    public Observable<Feed> getConstructorStandings() {
        return ergastApi.getConstructorStandings();
    }
}
