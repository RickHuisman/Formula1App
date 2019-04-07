package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentDrivers {

    @SerializedName("Driver")
    @Expose
    private Driver driver;

    @SerializedName("outQualified")
    @Expose
    private String outQualified;

    @SerializedName("raceCount")
    @Expose
    private String raceCount;

    @SerializedName("points")
    @Expose
    private String points;

    @SerializedName("highestFinish")
    @Expose
    private String highestFinish;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getOutQualified() {
        return outQualified;
    }

    public void setOutQualified(String outQualified) {
        this.outQualified = outQualified;
    }

    public String getRaceCount() {
        return raceCount;
    }

    public void setRaceCount(String raceCount) {
        this.raceCount = raceCount;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getHighestFinish() {
        return highestFinish;
    }

    public void setHighestFinish(String highestFinish) {
        this.highestFinish = highestFinish;
    }
}
