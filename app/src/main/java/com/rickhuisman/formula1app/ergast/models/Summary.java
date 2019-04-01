package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.SerializedName;

public class Summary {

    @SerializedName("raceWinner")
    private String raceWinner;

    @SerializedName("polePosition")
    private String polePosition;

    @SerializedName("FastestLap")
    private FastestLap2 fastestLap;

    @SerializedName("HighestClimber")
    private HighestClimber highestClimber;

    public String getRaceWinner() {
        return raceWinner;
    }

    public void setRaceWinner(String raceWinner) {
        this.raceWinner = raceWinner;
    }

    public String getPolePosition() {
        return polePosition;
    }

    public void setPolePosition(String polePosition) {
        this.polePosition = polePosition;
    }

    public FastestLap2 getFastestLap() {
        return fastestLap;
    }

    public void setFastestLap(FastestLap2 fastestLap) {
        this.fastestLap = fastestLap;
    }

    public HighestClimber getHighestClimber() {
        return highestClimber;
    }

    public void setHighestClimber(HighestClimber highestClimber) {
        this.highestClimber = highestClimber;
    }
}
