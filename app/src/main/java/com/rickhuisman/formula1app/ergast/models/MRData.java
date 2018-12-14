package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.SerializedName;

public class MRData {

    @SerializedName("RaceTable")
    private RaceTable raceTable;

    @SerializedName("StandingsTable")
    private StandingsTable standingsTable;

    public RaceTable getRaceTable() {
        return raceTable;
    }

    public void setRaceTable(RaceTable raceTable) {
        this.raceTable = raceTable;
    }

    public StandingsTable getStandingsTable() {
        return standingsTable;
    }

    public void setStandingsTable(StandingsTable standingsTable) {
        this.standingsTable = standingsTable;
    }
}
