package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.SerializedName;

public class FastestLap {

    @SerializedName("rank")
    private String rank;

    @SerializedName("lap")
    private String lap;

    @SerializedName("Time")
    private Time time;

    @SerializedName("AverageSpeed")
    private AverageSpeed averageSpeed;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getLap() {
        return lap;
    }

    public void setLap(String lap) {
        this.lap = lap;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public AverageSpeed getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(AverageSpeed averageSpeed) {
        this.averageSpeed = averageSpeed;
    }
}
