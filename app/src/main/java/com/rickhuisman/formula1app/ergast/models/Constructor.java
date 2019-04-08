package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Constructor {

    @SerializedName("constructorId")
    @Expose
    private String constructorId;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("nationality")
    @Expose
    private String nationality;

    @SerializedName("Seasons")
    @Expose
    private Seasons seasons;

    @SerializedName("raceWins")
    @Expose
    private String raceWins;

    @SerializedName("worldChampionships")
    @Expose
    private String worldChampionships;

    @SerializedName("driverWorldChampionships")
    @Expose
    private String driverWorldChampionships;

    @SerializedName("polePositions")
    @Expose
    private String polePositions;

    @SerializedName("grandPrixEntered")
    @Expose
    private String grandPrixEntered;

    @SerializedName("CurrentDrivers")
    @Expose
    private ArrayList<CurrentDrivers> currentDrivers;

    public String getConstructorId() {
        return constructorId;
    }

    public void setConstructorId(String constructorId) {
        this.constructorId = constructorId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Seasons getSeasons() {
        return seasons;
    }

    public void setSeasons(Seasons seasons) {
        this.seasons = seasons;
    }

    public String getRaceWins() {
        return raceWins;
    }

    public void setRaceWins(String raceWins) {
        this.raceWins = raceWins;
    }

    public String getWorldChampionships() {
        return worldChampionships;
    }

    public void setWorldChampionships(String worldChampionships) {
        this.worldChampionships = worldChampionships;
    }

    public String getDriverWorldChampionships() {
        return driverWorldChampionships;
    }

    public void setDriverWorldChampionships(String driverWorldChampionships) {
        this.driverWorldChampionships = driverWorldChampionships;
    }

    public String getPolePositions() {
        return polePositions;
    }

    public void setPolePositions(String polePositions) {
        this.polePositions = polePositions;
    }

    public String getGrandPrixEntered() {
        return grandPrixEntered;
    }

    public void setGrandPrixEntered(String grandPrixEntered) {
        this.grandPrixEntered = grandPrixEntered;
    }

    public ArrayList<CurrentDrivers> getCurrentDrivers() {
        return currentDrivers;
    }

    public void setCurrentDrivers(ArrayList<CurrentDrivers> currentDrivers) {
        this.currentDrivers = currentDrivers;
    }
}