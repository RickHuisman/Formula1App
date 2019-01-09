package com.rickhuisman.formula1app.ergast.test.db.dao;

import com.rickhuisman.formula1app.ergast.test.db.entities.Circuits;
import com.rickhuisman.formula1app.ergast.test.db.entities.Constructor;
import com.rickhuisman.formula1app.ergast.test.db.entities.Driver;
import com.rickhuisman.formula1app.ergast.test.db.entities.DriverStanding;
import com.rickhuisman.formula1app.ergast.test.db.entities.DriverStandingsWithDriver;
import com.rickhuisman.formula1app.ergast.test.db.entities.Qualifying;
import com.rickhuisman.formula1app.ergast.test.db.entities.QualifyingWithDriver;
import com.rickhuisman.formula1app.ergast.test.db.entities.RaceResultWithDriver;
import com.rickhuisman.formula1app.ergast.test.db.entities.RaceWithWinner;
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

    @Insert
    void insertCircuits(Circuits circuits);

    @Insert
    void insertConstructors(Constructor constructor);

    @Insert
    void insertQualifying(Qualifying qualifying);

    @Insert
    void insertDrivers(Driver driver);

    @Insert
    void insertDriverStandings(DriverStanding driverStanding);

    @Query("SELECT R.raceId AS race_raceId, year AS race_year, round AS race_round, R.circuitId AS race_circuitId, R.name AS race_name, date AS race_date, R.time AS race_time, R.url AS race_url, resultId AS result_resultId, S.raceId AS result_raceId, driverId AS result_driverId, S.constructorId AS result_constructorId, number AS result_number, grid AS result_grid, position AS result_position, positionText AS result_positionText, positionOrder AS result_positionOrder, points AS result_points, laps AS result_laps, S.time AS result_time, milliseconds AS result_milliseconds, fastestLap As result_fastestLap, rank AS result_rank, fastestLapTime AS result_fastestLapTime, fastestLapSpeed AS result_fastestLapSpeed, statusId AS result_statusId, S.constructorId AS constructor_constructorId, constructorRef AS constructor_constructorRef, T.name AS constructor_name, nationality AS constructor_nationality, C.url AS constructor_url, C.circuitId AS circuit_circuitId, circuitRef AS circuit_circuitRef, C.name AS circuit_name, location AS circuit_location, country AS circuit_country, lat AS circuit_lat, lng AS circuit_lng, alt AS circuit_alt, C.url AS circuit_url FROM races R JOIN results S ON S.raceId = R.raceId JOIN constructors T ON T.constructorId = S.constructorId JOIN circuits C ON C.circuitId = R.circuitId WHERE year = :year AND position = 1")
    LiveData<List<RaceWithWinner>> getRacesWithWinners(int year);

    @Query("SELECT resultId AS result_resultId, raceId AS result_raceId, R.driverId AS result_driverId, constructorId AS result_constructorId, R.number AS result_number, grid AS result_grid, position AS result_position, positionText AS result_positionText, positionOrder AS result_positionOrder, points AS result_points, laps AS result_laps, time AS result_time, milliseconds AS result_milliseconds, fastestLap AS result_fastestLap, rank AS result_rank, fastestLapTime AS result_fastestLapTime, fastestLapSpeed AS result_fastestLapSpeed, statusId AS result_statusId, D.driverId AS driver_driverId, driverRef AS driver_driverRef, D.number AS driver_number, D.code AS driver_code, forename AS driver_forename, surname AS driver_surname, dob AS driver_dob, nationality AS driver_nationality, D.url AS driver_url FROM results R JOIN drivers D ON R.driverId = D.driverId WHERE raceId = :raceId;")
    LiveData<List<RaceResultWithDriver>> getRaceResultWithDriver(int raceId);

    @Query("SELECT qualifyId AS qualifying_qualifyId, raceId AS qualifying_raceId, Q.driverId AS qualifying_driverId, constructorId AS qualifying_constructorId, Q.number AS qualifying_number, position AS qualifying_position, q1 AS qualifying_q1, q2 AS qualifying_q2, q3 AS qualifying_q3, D.driverId AS driver_driverId, driverRef AS driver_driverRef, D.number AS driver_number, D.code AS driver_code, forename AS driver_forename, surname AS driver_surname, dob AS driver_dob, nationality AS driver_nationality, D.url AS driver_url FROM qualifying Q JOIN drivers D ON Q.driverId = D.driverId WHERE raceId = :raceId")
    LiveData<List<QualifyingWithDriver>> getQualifyingWithDriver(int raceId);

    @Query("SELECT S.*, D.driverId AS driver_driverId, driverRef AS driver_driverRef, number AS driver_number, code AS driver_code, forename AS driver_forename, surname AS driver_surname, dob AS driver_dob, nationality AS driver_nationality, url AS driver_url FROM driverStandings S JOIN drivers D ON D.driverId = S.driverId WHERE raceId = :raceId ORDER BY points DESC")
    LiveData<List<DriverStandingsWithDriver>> getDriverStandingsWithDriver(int raceId);
}