package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Races {

    @SerializedName("season")
    private String season;

    @SerializedName("round")
    private String round;

    @SerializedName("raceName")
    private String raceName;

    @SerializedName("Circuit")
    private Circuit circuit;

    @SerializedName("LapRecord")
    private LapRecord lapRecord;

    @SerializedName("Summary")
    private Summary summary;

    @SerializedName("Constructor")
    private Constructor constructor;

    @SerializedName("date")
    private String date;

    @SerializedName("time")
    private String time;

    @SerializedName("PastWinners")
    private ArrayList<PastWinner> pastWinners;

    @SerializedName("QualifyingResults")
    private ArrayList<QualifyingResult> qualifyingResults;

    @SerializedName("Results")
    private ArrayList<Result> results;

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

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public LapRecord getLapRecord() {
        return lapRecord;
    }

    public void setLapRecord(LapRecord lapRecord) {
        this.lapRecord = lapRecord;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<PastWinner> getPastWinners() {
        return pastWinners;
    }

    public void setPastWinners(ArrayList<PastWinner> pastWinners) {
        this.pastWinners = pastWinners;
    }

    public ArrayList<QualifyingResult> getQualifyingResults() {
        return qualifyingResults;
    }

    public void setQualifyingResults(ArrayList<QualifyingResult> qualifyingResults) {
        this.qualifyingResults = qualifyingResults;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }
}