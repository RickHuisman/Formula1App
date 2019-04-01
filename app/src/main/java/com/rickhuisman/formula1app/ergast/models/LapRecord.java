package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.SerializedName;

public class LapRecord {

    @SerializedName("driverName")
    private String driverName;

    @SerializedName("lapTime")
    private String lapTime;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getLapTime() {
        return lapTime;
    }

    public void setLapTime(String lapTime) {
        this.lapTime = lapTime;
    }
}