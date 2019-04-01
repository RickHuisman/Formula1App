package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.SerializedName;

public class AverageSpeed {

    @SerializedName("units")
    private String units;

    @SerializedName("speed")
    private String speed;

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
