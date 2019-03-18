package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StandingsTable {

    @SerializedName("season")
    private String season;

    @SerializedName("StandingsLists")
    private ArrayList<StandingsLists> standingsLists;

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public ArrayList<StandingsLists> getStandingsLists() {
        return standingsLists;
    }

    public void setStandingsLists(ArrayList<StandingsLists> standingsLists) {
        this.standingsLists = standingsLists;
    }
}