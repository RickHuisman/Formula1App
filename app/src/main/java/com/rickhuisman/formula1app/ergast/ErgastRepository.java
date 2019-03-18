package com.rickhuisman.formula1app.ergast;

import android.app.Application;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.rickhuisman.formula1app.ergast.db.ErgastDatabase;
import com.rickhuisman.formula1app.ergast.db.dao.ErgastDao;
import com.rickhuisman.formula1app.ergast.db.entities.CircuitAndFirstGP;
import com.rickhuisman.formula1app.ergast.db.entities.Constructor;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorSeason;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorStandingsWithConstructorAndDrivers;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorWorldChampionship;
import com.rickhuisman.formula1app.ergast.db.entities.Driver;
import com.rickhuisman.formula1app.ergast.db.entities.DriverStandings;
import com.rickhuisman.formula1app.ergast.db.entities.HighestClimb;
import com.rickhuisman.formula1app.ergast.db.entities.HighestGrid;
import com.rickhuisman.formula1app.ergast.db.entities.HighestRaceFinish;
import com.rickhuisman.formula1app.ergast.db.entities.LapRecord;
import com.rickhuisman.formula1app.ergast.db.entities.PodiumCount;
import com.rickhuisman.formula1app.ergast.db.entities.QualifyingResult;
import com.rickhuisman.formula1app.ergast.db.entities.Race;
import com.rickhuisman.formula1app.ergast.db.entities.RaceCount;
import com.rickhuisman.formula1app.ergast.db.entities.RaceResult;
import com.rickhuisman.formula1app.ergast.db.entities.RaceResultDriver;
import com.rickhuisman.formula1app.ergast.db.entities.RaceWinner;
import com.rickhuisman.formula1app.ergast.db.entities.Result;
import com.rickhuisman.formula1app.ergast.db.entities.Team;
import com.rickhuisman.formula1app.ergast.db.entities.WorldChampionships;
import com.rickhuisman.formula1app.ergast.models.Feed;

import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ErgastRepository {

    private static final String BASE_URL = "http://188.166.27.87:8000/";
    private ErgastWebService mErgastApi;

    private ErgastDao mErgastDao;

    public ErgastRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mErgastApi = retrofit.create(ErgastWebService.class);
    }

    public ErgastRepository(Application application) {
        mErgastDao = ErgastDatabase.getInstance(application).mErgastDao();
    }

    public Observable<Feed> getRaceSchedule(int season) {
        return mErgastApi.getRaceSchedule(season);
    }

    public Observable<Feed> getDriverStandings() {
        return mErgastApi.getDriverStandings();
    }

    public Observable<Feed> getConstructorStandings() {
        return mErgastApi.getConstructorStandings();
    }

    public LiveData<List<RaceResult>> getRaceResult(int raceId) {
        return mErgastDao.getRaceResult(raceId);
    }

    public LiveData<List<QualifyingResult>> getQualifyingResult(int raceId) {
        return mErgastDao.getQualifyingResult(raceId);
    }

    public LiveData<List<DriverStandings>> getDriverStandings(int raceId) {
        return mErgastDao.getDriverStandings(raceId);
    }

    public LiveData<List<ConstructorStandingsWithConstructorAndDrivers>> getConstructorStandings(int raceId) {
        return mErgastDao.getConstructorStandings(raceId);
    }

    public LiveData<RaceWinner> getRaceWinner(int raceId) {
        return mErgastDao.getRaceWinner(raceId);
    }

    public LiveData<QualifyingResult> getPolePosition(int raceId) {
        return mErgastDao.getPolePosition(raceId);
    }

    public LiveData<HighestClimb> getHighestClimb(int raceId) {
        return mErgastDao.getHighestClimb(raceId);
    }

    public LiveData<Race> getRaceDate(int raceId) {
        return mErgastDao.getRaceDate(raceId);
    }

    public LiveData<CircuitAndFirstGP> getCircuitAndFirstGP(int circuitId) {
        return mErgastDao.getCircuitAndFirstGP(circuitId);
    }

    public LiveData<List<RaceResultDriver>> getRaceResultForCircuit(int circuitId) {
        return mErgastDao.getRaceResultForCircuit(circuitId);
    }

    public LiveData<Race> getRace(int raceId) {
        return mErgastDao.getRace(raceId);
    }

    public LiveData<Driver> getDriver(int driverId) {
        return mErgastDao.getDriver(driverId);
    }

    public LiveData<Result> getResultForDriver(int driverId) {
        return mErgastDao.getResultForDriver(driverId);
    }

    public LiveData<RaceCount> getRaceCount(int driverId) {
        return mErgastDao.getRaceCount(driverId);
    }

    public LiveData<PodiumCount> getPodiumsCount(int driverId) {
        return mErgastDao.getPodiumsCount(driverId);
    }

    public LiveData<HighestRaceFinish> getHighestRaceFinish(int driverId) {
        return mErgastDao.getHighestRaceFinish(driverId);
    }

    public LiveData<HighestGrid> getHighestGrid(int driverId) {
        return mErgastDao.getHighestGrid(driverId);
    }

    public LiveData<List<WorldChampionships>> getWorldChampionships(int driverId) {
        return mErgastDao.getWorldChampionships(driverId);
    }

    public LiveData<List<Team>> getTeams(int driverId) {
        return mErgastDao.getTeams(driverId);
    }

    public LiveData<Constructor> getConstructor(int constructorId) {
        return mErgastDao.getConstructor(constructorId);
    }

    public LiveData<ConstructorSeason> getSeasonsFor(int constructorId) {
        return mErgastDao.getSeasonsFor(constructorId);
    }

    public LiveData<List<ConstructorWorldChampionship>> getWorldChampionshipsFor(int constructorId) {
        return mErgastDao.getWorldChampionshipsFor(constructorId);
    }

    public LiveData<RaceCount> getPolePositionsFor(int constructorId) {
        return mErgastDao.getPolePositionsFor(constructorId);
    }

    public LiveData<RaceCount> getRaceWinsFor(int constructorId) {
        return mErgastDao.getRaceWinsFor(constructorId);
    }

    public LiveData<RaceCount> getRaceCountFor(int constructorId) {
        return mErgastDao.getRaceCountFor(constructorId);
    }

    public LiveData<LapRecord> getLapRecordFor(int circuitId) {
        return mErgastDao.getLapRecordFor(circuitId);
    }
//
//    public LiveData<List<Result>> getResultsForDriverId(int driverId, int year) {
//        return mErgastDao.getResultsForDriverId(driverId, year);
//    }
}
