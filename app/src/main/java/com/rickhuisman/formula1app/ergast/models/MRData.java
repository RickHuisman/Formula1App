package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.SerializedName;

public class MRData {

    @SerializedName("RaceTable")
    private RaceTable raceTable;

    @SerializedName("StandingsTable")
    private StandingsTable standingsTable;

    @SerializedName("DriverTable")
    private DriverTable driverTable;

    @SerializedName("ConstructorTable")
    private ConstructorTable constructorTable;

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

    public DriverTable getDriverTable() {
        return driverTable;
    }

    public void setDriverTable(DriverTable driverTable) {
        this.driverTable = driverTable;
    }

    public ConstructorTable getConstructorTable() {
        return constructorTable;
    }

    public void setConstructorTable(ConstructorTable constructorTable) {
        this.constructorTable = constructorTable;
    }
}