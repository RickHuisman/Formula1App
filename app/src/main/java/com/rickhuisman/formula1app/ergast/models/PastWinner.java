package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.SerializedName;

public class PastWinner {

    @SerializedName("year")
    private String year;

    @SerializedName("time")
    private String time;

    @SerializedName("grid")
    private String grid;

    @SerializedName("driverName")
    private String driverName;

    @SerializedName("constructorId")
    private String constructorId;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getConstructorId() {
        return constructorId;
    }

    public void setConstructorId(String constructorId) {
        this.constructorId = constructorId;
    }
}