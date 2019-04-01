package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.SerializedName;

public class HighestClimber {

    @SerializedName("Driver")
    private Driver driver;

    @SerializedName("positions")
    private String positions;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }
}
