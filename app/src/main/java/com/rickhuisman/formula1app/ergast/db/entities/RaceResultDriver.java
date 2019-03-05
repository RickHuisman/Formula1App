package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;

public class RaceResultDriver {

    @ColumnInfo(name = "year")
    private int year;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "grid")
    private int grid;

    @NonNull
    @Embedded()
    private Driver driver;

    @ColumnInfo(name = "constructorId")
    private int constructorId;

    public RaceResultDriver(int year, String time, int grid, @NonNull Driver driver, int constructorId) {
        this.year = year;
        this.time = time;
        this.grid = grid;
        this.driver = driver;
        this.constructorId = constructorId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getGrid() {
        return grid;
    }

    public void setGrid(int grid) {
        this.grid = grid;
    }

    @NonNull
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(@NonNull Driver driver) {
        this.driver = driver;
    }

    public int getConstructorId() {
        return constructorId;
    }

    public void setConstructorId(int constructorId) {
        this.constructorId = constructorId;
    }
}
