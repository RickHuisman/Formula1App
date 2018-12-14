package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StandingsLists {

    @SerializedName("season")
    private String season;

    @SerializedName("round")
    private String round;

    @SerializedName("DriverStandings")
    private ArrayList<DriverStandings> driverStandings;

    @SerializedName("ConstructorStandings")
    private ArrayList<ConstructorStandings> constructorStandings;

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public ArrayList<DriverStandings> getDriverStandings() {
        return driverStandings;
    }

    public void setDriverStandings(ArrayList<DriverStandings> driverStandings) {
        this.driverStandings = driverStandings;
    }

    public ArrayList<ConstructorStandings> getConstructorStandings() {
        return constructorStandings;
    }

    public void setConstructorStandings(ArrayList<ConstructorStandings> constructorStandings) {
        this.constructorStandings = constructorStandings;
    }
}
