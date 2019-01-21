package com.rickhuisman.formula1app.ergast;

import android.app.Application;

import com.rickhuisman.formula1app.ergast.db.Formula1Database;
import com.rickhuisman.formula1app.ergast.db.dao.Formula1Dao;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorStandingsWithConstructor;
import com.rickhuisman.formula1app.ergast.db.entities.DriverStandingsWithDriver;
import com.rickhuisman.formula1app.ergast.db.entities.QualifyingWithDriver;
import com.rickhuisman.formula1app.ergast.db.entities.RaceResultWithDriverAndStatus;
import com.rickhuisman.formula1app.ergast.db.entities.RaceWithWinner;

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

    public LiveData<List<DriverStandingsWithDriver>> getDriverStandingsWithDriver(int raceId) {
        return formula1Dao.getDriverStandingsWithDriver(raceId);
    }

    public LiveData<List<ConstructorStandingsWithConstructor>> getConstructorStandingsWithConstructor(int raceId) {
        return formula1Dao.getConstructorStandingsWithConstructor(raceId);
    }
}
