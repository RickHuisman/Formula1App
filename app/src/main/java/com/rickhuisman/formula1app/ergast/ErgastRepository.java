package com.rickhuisman.formula1app.ergast;

import android.app.Application;

import com.rickhuisman.formula1app.ergast.db.Formula1Database;
import com.rickhuisman.formula1app.ergast.db.dao.Formula1Dao;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorStandingsWithConstructorAndDrivers;
import com.rickhuisman.formula1app.ergast.db.entities.DriverStandingsWithDriverAndConstructor;
import com.rickhuisman.formula1app.ergast.db.entities.QualifyingWithDriver;
import com.rickhuisman.formula1app.ergast.db.entities.RaceResultWithDriverAndStatus;
import com.rickhuisman.formula1app.ergast.db.entities.RaceWithWinner;
import com.rickhuisman.formula1app.ergast.db.entities.Results;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ErgastRepository {
    private Formula1Dao formula1Dao;

    public ErgastRepository(Application application) {
        Formula1Database db = Formula1Database.getInstance(application);
        formula1Dao = db.noteDao();
    }

    public LiveData<List<RaceWithWinner>> getRacesAndWinners(int year) {
        return formula1Dao.getRacesWithWinners(year);
    }

    public LiveData<List<RaceResultWithDriverAndStatus>> getRaceResultWithDriverAndStatus(int raceId) {
        return formula1Dao.getRaceResultWithDriverAndStatus(raceId);
    }

    public LiveData<List<QualifyingWithDriver>> getQualifyingWithDriver(int raceId) {
        return formula1Dao.getQualifyingWithDriver(raceId);
    }

    public LiveData<List<DriverStandingsWithDriverAndConstructor>> getDriverStandingsWithDriverAndConstructor(int raceId) {
        return formula1Dao.getDriverStandingsWithDriverAndConstructor(raceId);
    }

    public LiveData<List<ConstructorStandingsWithConstructorAndDrivers>> getConstructorStandingsWithConstructorAndDrivers(int constructorId, int raceId) {
        return formula1Dao.getConstructorStandingsWithConstructorAndDrivers(constructorId, raceId);
    }

    public LiveData<List<Results>> getResultsForDriverId(int driverId, int year) {
        return formula1Dao.getResultsForDriverId(driverId, year);
    }
}
