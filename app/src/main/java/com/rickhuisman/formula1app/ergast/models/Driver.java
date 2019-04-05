package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Driver {

    @SerializedName("driverId")
    @Expose
    private String driverId;

    @SerializedName("permanentNumber")
    @Expose
    private String permanentNumber;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("givenName")
    @Expose
    private String givenName;

    @SerializedName("familyName")
    @Expose
    private String familyName;

    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;

    @SerializedName("nationality")
    @Expose
    private String nationality;

    @SerializedName("worldChampionships")
    @Expose
    private String worldChampionships;

    @SerializedName("podiums")
    @Expose
    private String podiums;

    @SerializedName("grandPrixEntered")
    @Expose
    private String grandPrixEntered;

    @SerializedName("HighestRaceFinish")
    @Expose
    private HighestRaceFinish highestRaceFinish;

    @SerializedName("HighestGridPosition")
    @Expose
    private HighestGridPosition highestGridPosition;

    @SerializedName("Teams")
    @Expose
    private ArrayList<Team> teams;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getPermanentNumber() {
        return permanentNumber;
    }

    public void setPermanentNumber(String permanentNumber) {
        this.permanentNumber = permanentNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getWorldChampionships() {
        return worldChampionships;
    }

    public void setWorldChampionships(String worldChampionships) {
        this.worldChampionships = worldChampionships;
    }

    public String getPodiums() {
        return podiums;
    }

    public void setPodiums(String podiums) {
        this.podiums = podiums;
    }

    public String getGrandPrixEntered() {
        return grandPrixEntered;
    }

    public void setGrandPrixEntered(String grandPrixEntered) {
        this.grandPrixEntered = grandPrixEntered;
    }

    public HighestRaceFinish getHighestRaceFinish() {
        return highestRaceFinish;
    }

    public void setHighestRaceFinish(HighestRaceFinish highestRaceFinish) {
        this.highestRaceFinish = highestRaceFinish;
    }

    public HighestGridPosition getHighestGridPosition() {
        return highestGridPosition;
    }

    public void setHighestGridPosition(HighestGridPosition highestGridPosition) {
        this.highestGridPosition = highestGridPosition;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }
}