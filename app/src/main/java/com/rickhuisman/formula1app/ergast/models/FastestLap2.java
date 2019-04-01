package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.SerializedName;

public class FastestLap2 {

    @SerializedName("Driver")
    private Driver driver;

    @SerializedName("lapTime")
    private String lapTime;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getLapTime() {
        return lapTime;
    }

    public void setLapTime(String lapTime) {
        this.lapTime = lapTime;
    }
}
