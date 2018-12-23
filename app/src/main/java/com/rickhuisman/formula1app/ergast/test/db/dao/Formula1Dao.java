package com.rickhuisman.formula1app.ergast.test.db.dao;

import com.rickhuisman.formula1app.ergast.test.db.entities.Races;
import com.rickhuisman.formula1app.ergast.test.db.entities.Results;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface Formula1Dao {

    @Insert
    void insertRaces(Races races);

    @Insert
    void insertResults(Results results);

    @Query("SELECT * FROM races WHERE year = :year")
    LiveData<List<Races>> getAllRaces(int year);

    @Query("SELECT * FROM results WHERE raceId >= 989")
    LiveData<List<Results>> getAllResults();

    class RaceAndWinner {
        public int raceId;

        public int driverId;
    }

    @Query("SELECT * FROM races R JOIN results S ON S.raceId = R.raceId WHERE R.raceId >= 989 AND position = 1")
    RaceAndWinner[] getRacesAndWinners();
}