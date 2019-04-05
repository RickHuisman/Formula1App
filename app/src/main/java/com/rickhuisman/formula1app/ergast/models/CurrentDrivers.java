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
}
