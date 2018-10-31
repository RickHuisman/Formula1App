package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.SerializedName;

public class MRData {

    @SerializedName("RaceTable")
    private RaceTable raceTable;

    public RaceTable getRaceTable() {
        return raceTable;
    }

    public void setRaceTable(RaceTable raceTable) {
        this.raceTable = raceTable;
    }
}
