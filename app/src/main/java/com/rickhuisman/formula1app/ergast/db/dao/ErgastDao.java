package com.rickhuisman.formula1app.ergast.db.dao;

import com.rickhuisman.formula1app.ergast.db.entities.Circuit;
import com.rickhuisman.formula1app.ergast.db.entities.CircuitAndFirstGP;
import com.rickhuisman.formula1app.ergast.db.entities.Constructor;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorResult;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorSeason;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorStandings;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorStandingsWithConstructorAndDrivers;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorWorldChampionship;
import com.rickhuisman.formula1app.ergast.db.entities.Driver;
import com.rickhuisman.formula1app.ergast.db.entities.DriverStanding;
import com.rickhuisman.formula1app.ergast.db.entities.DriverStandings;
import com.rickhuisman.formula1app.ergast.db.entities.HighestClimb;
import com.rickhuisman.formula1app.ergast.db.entities.HighestGrid;
import com.rickhuisman.formula1app.ergast.db.entities.HighestRaceFinish;
import com.rickhuisman.formula1app.ergast.db.entities.PodiumCount;
import com.rickhuisman.formula1app.ergast.db.entities.Qualifying;
import com.rickhuisman.formula1app.ergast.db.entities.QualifyingResult;
import com.rickhuisman.formula1app.ergast.db.entities.Race;
import com.rickhuisman.formula1app.ergast.db.entities.RaceCount;
import com.rickhuisman.formula1app.ergast.db.entities.RaceResult;
import com.rickhuisman.formula1app.ergast.db.entities.RaceResultDriver;
import com.rickhuisman.formula1app.ergast.db.entities.RaceWinner;
import com.rickhuisman.formula1app.ergast.db.entities.RaceWithWinner;
import com.rickhuisman.formula1app.ergast.db.entities.Result;
import com.rickhuisman.formula1app.ergast.db.entities.Status;
import com.rickhuisman.formula1app.ergast.db.entities.Team;
import com.rickhuisman.formula1app.ergast.db.entities.WorldChampionships;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ErgastDao {

    @Insert
    void insertRaces(Race race);

    @Insert
    void insertResults(Result result);

    @Insert
    void insertCircuits(Circuit circuit);

    @Insert
    void insertConstructors(Constructor constructor);

    @Insert
    void insertQualifying(Qualifying qualifying);

    @Insert
    void insertDrivers(Driver driver);

    @Insert
    void insertDriverStandings(DriverStanding driverStanding);

    @Insert
    void insertConstructorStandings(ConstructorStandings constructorStandings);

    @Insert
    void insertConstructorResults(ConstructorResult constructorResult);

    @Insert
    void insertStatus(Status status);

    @Query("SELECT R.raceId AS race_raceId, year AS race_year, round AS race_round, R.circuitId AS race_circuitId, R.name AS race_name, date AS race_date, R.time AS race_time, R.url AS race_url, resultId AS result_resultId, S.raceId AS result_raceId, driverId AS result_driverId, S.constructorId AS result_constructorId, number AS result_number, grid AS result_grid, position AS result_position, positionText AS result_positionText, positionOrder AS result_positionOrder, points AS result_points, laps AS result_laps, S.time AS result_time, milliseconds AS result_milliseconds, fastestLap As result_fastestLap, rank AS result_rank, fastestLapTime AS result_fastestLapTime, fastestLapSpeed AS result_fastestLapSpeed, statusId AS result_statusId, S.constructorId AS constructor_constructorId, constructorRef AS constructor_constructorRef, T.name AS constructor_name, nationality AS constructor_nationality, C.url AS constructor_url, C.circuitId AS circuit_circuitId, circuitRef AS circuit_circuitRef, C.name AS circuit_name, location AS circuit_location, country AS circuit_country, lat AS circuit_lat, lng AS circuit_lng, alt AS circuit_alt, C.url AS circuit_url FROM races R JOIN results S ON S.raceId = R.raceId JOIN constructors T ON T.constructorId = S.constructorId JOIN circuits C ON C.circuitId = R.circuitId WHERE year = :year AND position = 1")
    LiveData<List<RaceWithWinner>> getRaceSchedule(int year);

    @Query("SELECT resultId AS result_resultId, raceId AS result_raceId, R.driverId AS result_driverId, constructorId AS result_constructorId, R.number AS result_number, grid AS result_grid, position AS result_position, positionText AS result_positionText, positionOrder AS result_positionOrder, points AS result_points, laps AS result_laps, time AS result_time, milliseconds AS result_milliseconds, fastestLap AS result_fastestLap, rank AS result_rank, fastestLapTime AS result_fastestLapTime, fastestLapSpeed AS result_fastestLapSpeed, R.statusId AS result_statusId, D.driverId AS driver_driverId, driverRef AS driver_driverRef, D.number AS driver_number, D.code AS driver_code, forename AS driver_forename, surname AS driver_surname, dob AS driver_dob, D.nationality AS driver_nationality, D.url AS driver_url, S.statusId AS status_statusId, S.status AS status_status FROM results R JOIN drivers D ON R.driverId = D.driverId JOIN status S ON S.statusId = R.statusId WHERE raceId = :raceId")
    LiveData<List<RaceResult>> getRaceResult(int raceId);

    @Query("SELECT qualifyId AS qualifying_qualifyId, raceId AS qualifying_raceId, Q.driverId AS qualifying_driverId, constructorId AS qualifying_constructorId, Q.number AS qualifying_number, position AS qualifying_position, q1 AS qualifying_q1, q2 AS qualifying_q2, q3 AS qualifying_q3, D.driverId AS driver_driverId, driverRef AS driver_driverRef, D.number AS driver_number, D.code AS driver_code, forename AS driver_forename, surname AS driver_surname, dob AS driver_dob, D.nationality AS driver_nationality, D.url AS driver_url FROM qualifying Q JOIN drivers D ON Q.driverId = D.driverId WHERE raceId = :raceId ORDER BY position")
    LiveData<List<QualifyingResult>> getQualifyingResult(int raceId);

    @Query("SELECT S.*, resultId AS result_resultId, R.raceId AS result_raceId, R.driverId AS result_driverId, R.constructorId AS result_constructorId, R.number AS result_number, grid AS result_grid, R.position AS result_position, R.positionText AS result_positionText, positionOrder AS result_positionOrder, R.points AS result_points, laps AS result_laps, time AS result_time, milliseconds AS result_milliseconds, fastestLap AS result_fastestLap, rank AS result_rank, fastestLapTime AS result_fastestLapTime, fastestLapSpeed AS result_fastestLapSpeed, R.statusId AS result_statusId, D.driverId AS driver_driverId, driverRef AS driver_driverRef, D.number AS driver_number, code AS driver_code, forename AS driver_forename, surname AS driver_surname, dob AS driver_dob, D.nationality AS driver_nationality, D.url AS driver_url, C.constructorId AS constructor_constructorId, constructorRef AS constructor_constructorRef, C.name AS constructor_name, C.nationality AS constructor_nationality, C.url AS constructor_url FROM driverStandings S JOIN results R ON S.raceId = R.raceId AND D.driverId = R.driverId JOIN drivers D ON S.driverId = D.driverId JOIN constructors C ON R.constructorId = C.constructorId WHERE S.raceId = :raceId ORDER BY S.points DESC")
    LiveData<List<DriverStandings>> getDriverStandings(int raceId);

    @Query("SELECT S.*, R.resultId AS result_resultId, R.raceId AS result_raceId, R.driverId AS result_driverId, R.constructorId AS result_constructorId, R.number AS result_number, R.grid AS result_grid, R.position AS result_position, R.positionText AS result_positionText, R.positionOrder AS result_positionOrder, R.points AS result_points, R.laps AS result_laps, R.time AS result_time, milliseconds AS result_milliseconds, fastestLap As result_fastestLap, rank AS result_rank, fastestLapTime AS result_fastestLapTime, fastestLapSpeed AS result_fastestLapSpeed, statusId AS result_statusId, D.driverId AS driver_driverId, driverRef AS driver_driverRef, D.number AS driver_number, D.code AS driver_code, forename AS driver_forename, surname AS driver_surname, dob AS driver_dob, D.nationality AS driver_nationality, D.url AS driver_url, C.constructorId AS constructor_constructorId, constructorRef AS constructor_constructorRef, C.name AS constructor_name, C.nationality AS constructor_nationality, C.url AS constructor_url FROM constructorStandings S JOIN results R ON S.raceId = R.raceId AND S.constructorId = R.constructorId JOIN constructors C ON S.constructorId = C.constructorId JOIN drivers D ON R.driverId = D.driverId WHERE S.raceId = :raceId GROUP BY C.constructorId ORDER BY S.points DESC")
    LiveData<List<ConstructorStandingsWithConstructorAndDrivers>> getConstructorStandings(int raceId);

    @Query("SELECT resultId AS result_resultId, raceId AS result_raceId, R.driverId AS result_driverId, constructorId AS result_constructorId, R.number AS result_number, grid AS result_grid, position AS result_position, positionText AS result_positionText, positionOrder AS result_positionOrder, points AS result_points, laps AS result_laps, time AS result_time, milliseconds AS result_milliseconds, fastestLap AS result_fastestLap, rank AS result_rank, fastestLapTime AS result_fastestLapTime, fastestLapSpeed AS result_fastestLapSpeed, R.statusId AS result_statusId, D.driverId AS driver_driverId, driverRef AS driver_driverRef, D.number AS driver_number, D.code AS driver_code, forename AS driver_forename, surname AS driver_surname, dob AS driver_dob, D.nationality AS driver_nationality, D.url AS driver_url FROM results R JOIN drivers D ON R.driverId = D.driverId WHERE raceId = :raceId AND position = 1;")
    LiveData<RaceWinner> getRaceWinner(int raceId);

    @Query("SELECT qualifyId AS qualifying_qualifyId, raceId AS qualifying_raceId, Q.driverId AS qualifying_driverId, constructorId AS qualifying_constructorId, Q.number AS qualifying_number, position AS qualifying_position, q1 AS qualifying_q1, q2 AS qualifying_q2, q3 AS qualifying_q3, D.driverId AS driver_driverId, driverRef AS driver_driverRef, D.number AS driver_number, D.code AS driver_code, forename AS driver_forename, surname AS driver_surname, dob AS driver_dob, D.nationality AS driver_nationality, D.url AS driver_url FROM qualifying Q JOIN drivers D ON Q.driverId = D.driverId WHERE raceId = :raceId AND position = 1;")
    LiveData<QualifyingResult> getPolePosition(int raceId);

    @Query("SELECT D.*, (grid - position) AS climb FROM races R JOIN results S ON R.raceId = S.raceId JOIN drivers D ON S.driverId = D.driverId WHERE R.raceId = :raceId AND position != 0 ORDER BY climb DESC LIMIT 1")
    LiveData<HighestClimb> getHighestClimb(int raceId);

    @Query("SELECT * FROM races WHERE raceId = :raceId")
    LiveData<Race> getRaceDate(int raceId);

    @Query("SELECT * FROM races R JOIN circuits C ON R.circuitId = C.circuitId WHERE R.circuitId = :circuitId ORDER BY year LIMIT 1")
    LiveData<CircuitAndFirstGP> getCircuitAndFirstGP(int circuitId);

    @Query("SELECT year, S.time, grid, D.*, constructorId FROM races R JOIN results S ON R.raceId = S.raceId JOIN drivers D ON S.driverId = D.driverId WHERE R.circuitId = :circuitId AND position = 1 ORDER BY year DESC LIMIT 3")
    LiveData<List<RaceResultDriver>> getRaceResultForCircuit(int circuitId);

    @Query("SELECT * FROM races WHERE raceId = :raceId")
    LiveData<Race> getRace(int raceId);

    @Query("SELECT * FROM drivers WHERE driverId = :driverId")
    LiveData<Driver> getDriver(int driverId);

    @Query("SELECT * FROM results WHERE driverId = :driverId ORDER BY raceId DESC LIMIT 1")
    LiveData<Result> getResultForDriver(int driverId);

    @Query("SELECT S.*, R.raceId AS race_raceId, year AS race_year, round AS race_round, R.circuitId AS race_circuitId, R.name AS race_name, date AS race_date, R.time AS race_time, R.url AS race_url FROM driverStandings S JOIN races R ON S.raceId = R.raceId WHERE driverId = :driverId GROUP BY year HAVING position = 1")
    LiveData<List<WorldChampionships>> getWorldChampionships(int driverId);

    @Query("SELECT *, COUNT() AS count FROM results WHERE driverId = :driverId AND position != 0 GROUP BY position LIMIT 1")
    LiveData<HighestRaceFinish> getHighestRaceFinish(int driverId);

    @Query("SELECT *, COUNT () AS count FROM qualifying WHERE driverId = :driverId GROUP BY position ORDER BY position LIMIT 1")
    LiveData<HighestGrid> getHighestGrid(int driverId);

    @Query("SELECT count() AS count FROM results WHERE driverId = :driverId AND position <= 3")
    LiveData<PodiumCount> getPodiumsCount(int driverId);

    @Query("SELECT count() AS count FROM results WHERE driverId = :driverId")
    LiveData<RaceCount> getRaceCount(int driverId);

    @Query("SELECT C.*, MIN(year) AS year_start, MAX(year) AS year_end FROM results S JOIN races R ON S.raceId = R.raceId JOIN constructors C ON S.constructorId = C.constructorId WHERE driverId = :driverId GROUP BY C.constructorId ORDER BY year_end DESC")
    LiveData<List<Team>> getTeams(int driverId);

    @Query("SELECT * FROM constructors WHERE constructorId = :constructorId")
    LiveData<Constructor> getConstructor(int constructorId);

    @Query("SELECT MIN(year) AS year_start, MAX(year) AS year_end FROM constructorResults C JOIN races R ON C.raceId = R.raceId WHERE C.constructorId = :constructorId")
    LiveData<ConstructorSeason> getSeasonsFor(int constructorId);

    @Query("SELECT C.*, R.raceId AS race_raceId, year AS race_year, round AS race_round, R.circuitId AS race_circuitId, R.name AS race_name, date AS race_date, R.time AS race_time, R.url AS race_url FROM constructorStandings C JOIN races R ON C.raceId = R.raceID WHERE constructorID = :constructorId GROUP BY year HAVING position = 1;")
    LiveData<List<ConstructorWorldChampionship>> getWorldChampionshipsFor(int constructorId);

//    @Query("SELECT * FROM driverStandings S JOIN races R ON S.raceId = R.raceId WHERE driverId = 20 GROUP BY year HAVING position = 1")
//    LiveData<List<ConstructorWorldChampionship>> getDriverWorldChampionshipsFor(int constructorId);

    @Query("SELECT COUNT() AS count FROM qualifying WHERE position = 1 AND constructorId = :constructorId")
    LiveData<RaceCount> getPolePositionsFor(int constructorId);

    @Query("SELECT COUNT() AS count FROM results WHERE position = 1 AND constructorId = :constructorId")
    LiveData<RaceCount> getRaceWinsFor(int constructorId);

    @Query("SELECT COUNT() AS count FROM constructorResults WHERE constructorId = :constructorId")
    LiveData<RaceCount> getRaceCountFor(int constructorId);

//    @Query("SELECT * FROM results S JOIN races R ON R.raceId = S.raceId WHERE driverId = :driverId AND year = :year")
//    LiveData<List<Result>> getResultsForDriverId(int driverId, int year);
}