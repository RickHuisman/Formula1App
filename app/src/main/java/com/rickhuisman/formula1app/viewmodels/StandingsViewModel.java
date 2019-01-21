package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;

import com.rickhuisman.formula1app.ergast.ErgastRepository;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorStandingsWithConstructor;
import com.rickhuisman.formula1app.ergast.db.entities.DriverStandingsWithDriver;

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

    public LiveData<List<DriverStandingsWithDriver>> getDriverStandingsWithDriver(int raceId) {
        return mErgastRepository.getDriverStandingsWithDriver(raceId);
    }

    public LiveData<List<ConstructorStandingsWithConstructor>> getConstructorStandingsWithConstructor(int raceId) {
        return mErgastRepository.getConstructorStandingsWithConstructor(raceId);
    }
}
