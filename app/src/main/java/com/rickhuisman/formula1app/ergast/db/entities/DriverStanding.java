package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "driverStandings")
public class DriverStanding {

    @PrimaryKey
    @ColumnInfo(name = "driverStandingsId")
    private int driverStandingsId;

    @NonNull
    @ColumnInfo(name = "raceId")
    private int raceId;

    @NonNull
    @ColumnInfo(name = "driverId")
    private int driverId;

    @NonNull
    @ColumnInfo(name = "points")
    private float points;

    @ColumnInfo(name = "position")
    private int position;

    @ColumnInfo(name = "positionText")
    private String positionText;

    @NonNull
    @ColumnInfo(name = "wins")
    private int wins;

    public DriverStanding(int driverStandingsId, @NonNull int raceId, @NonNull int driverId, @NonNull float points, int position, String positionText, @NonNull int wins) {
        this.driverStandingsId = driverStandingsId;
        this.raceId = raceId;
        this.driverId = driverId;
        this.points = points;
        this.position = position;
        this.positionText = positionText;
        this.wins = wins;
    }

    public int getDriverStandingsId() {
        return driverStandingsId;
    }

    public void setDriverStandingsId(int driverStandingsId) {
        this.driverStandingsId = driverStandingsId;
    }

    @NonNull
    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(@NonNull int raceId) {
        this.raceId = raceId;
    }

    @NonNull
    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(@NonNull int driverId) {
        this.driverId = driverId;
    }

    @NonNull
    public float getPoints() {
        return points;
    }

    public void setPoints(@NonNull float points) {
        this.points = points;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPositionText() {
        return positionText;
    }

    public void setPositionText(String positionText) {
        this.positionText = positionText;
    }

    @NonNull
    public int getWins() {
        return wins;
    }

    public void setWins(@NonNull int wins) {
        this.wins = wins;
    }
}
