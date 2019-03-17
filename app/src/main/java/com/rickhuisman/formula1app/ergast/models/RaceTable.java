package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RaceTable {

    @SerializedName("season")
    private String season;

    @SerializedName("Races")
    private ArrayList<Races> races;

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public ArrayList<Races> getRaces() {
        return races;
    }

    public void setRaces(ArrayList<Races> races) {
        this.races = races;
    }
}