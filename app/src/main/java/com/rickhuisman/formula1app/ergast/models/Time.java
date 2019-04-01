package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.SerializedName;

public class Time {

    @SerializedName("millis")
    private String milliSeconds;

    @SerializedName("time")
    private String time;

    public String getMilliSeconds() {
        return milliSeconds;
    }

    public void setMilliSeconds(String milliSeconds) {
        this.milliSeconds = milliSeconds;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
