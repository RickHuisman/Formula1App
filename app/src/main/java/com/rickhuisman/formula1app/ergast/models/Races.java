package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.SerializedName;

public class Races {

    @SerializedName("season")
    private String season;

    @SerializedName("round")
    private String round;

    @SerializedName("raceName")
    private String raceName;

    @SerializedName("Circuit")
    private Circuit circuit;

    @SerializedName("Constructor")
    private Constructor constructor;

    @SerializedName("date")
    private String date;

    @SerializedName("time")
    private String time;

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
}