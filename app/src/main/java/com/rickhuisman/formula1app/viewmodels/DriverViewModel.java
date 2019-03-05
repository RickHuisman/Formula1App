package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;

import com.rickhuisman.formula1app.ergast.ErgastRepository;
import com.rickhuisman.formula1app.ergast.db.entities.Driver;
import com.rickhuisman.formula1app.ergast.db.entities.HighestGrid;
import com.rickhuisman.formula1app.ergast.db.entities.HighestRaceFinish;
import com.rickhuisman.formula1app.ergast.db.entities.PodiumCount;
import com.rickhuisman.formula1app.ergast.db.entities.RaceCount;
import com.rickhuisman.formula1app.ergast.db.entities.Result;
import com.rickhuisman.formula1app.ergast.db.entities.Team;
import com.rickhuisman.formula1app.ergast.db.entities.WorldChampionships;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class DriverViewModel extends AndroidViewModel {

    private ErgastRepository mErgastRepository;

    public DriverViewModel(@NonNull Application application) {
        super(application);
        mErgastRepository = new ErgastRepository(application);
    }

    public LiveData<Driver> getDriver(int driverId) {
        return mErgastRepository.getDriver(driverId);
    }

    public LiveData<Result> getResultForDriver(int driverId) {
        return mErgastRepository.getResultForDriver(driverId);
    }

    public LiveData<RaceCount> getRaceCount(int driverId) {
        return mErgastRepository.getRaceCount(driverId);
    }

    public LiveData<PodiumCount> getPodiumsCount(int driverId) {
        return mErgastRepository.getPodiumsCount(driverId);
    }

    public LiveData<HighestRaceFinish> getHighestRaceFinish(int driverId) {
        return mErgastRepository.getHighestRaceFinish(driverId);
    }

    public LiveData<HighestGrid> getHighestGrid(int driverId) {
        return mErgastRepository.getHighestGrid(driverId);
    }

    public LiveData<List<WorldChampionships>> getWorldChampionships(int driverId) {
        return mErgastRepository.getWorldChampionships(driverId);
    }

    public LiveData<List<Team>> getTeams(int driverId) {
        return mErgastRepository.getTeams(driverId);
    }
//    public LiveData<List<Result>> getResultsForDriverId(int driverId, int year) {
//        return mErgastRepository.getResultsForDriverId(driverId, year);
//    }
}
