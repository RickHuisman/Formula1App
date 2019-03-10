package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "lapTimes", primaryKeys = {"raceId", "driverId", "lap"})
public class LapTimes {

    @ColumnInfo(name = "raceId")
    private int raceId;

    @ColumnInfo(name = "driverId")
    private int driverId;

    @ColumnInfo(name = "lap")
    private int lap;

    @ColumnInfo(name = "position")
    private int position;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "milliseconds")
    private int milliseconds;

    public LapTimes(int raceId, int driverId, int lap, int position, String time, int milliseconds) {
        this.raceId = raceId;
        this.driverId = driverId;
        this.lap = lap;
        this.position = position;
        this.time = time;
        this.milliseconds = milliseconds;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getLap() {
        return lap;
    }

    public void setLap(int lap) {
        this.lap = lap;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }
}