package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;

import com.rickhuisman.formula1app.ergast.ErgastRepository;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorStandingsWithConstructorAndDrivers;
import com.rickhuisman.formula1app.ergast.db.entities.DriverStandings;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class StandingsViewModel extends AndroidViewModel {

    private ErgastRepository mErgastRepository;

    public StandingsViewModel(@NonNull Application application) {
        super(application);
        mErgastRepository = new ErgastRepository(application);
    }

    public LiveData<List<DriverStandings>> getDriverStandings(int raceId) {
        return mErgastRepository.getDriverStandings(raceId);
    }

    public LiveData<List<ConstructorStandingsWithConstructorAndDrivers>> getConstructorStandings(int raceId) {
        return mErgastRepository.getConstructorStandings(raceId);
    }
}
