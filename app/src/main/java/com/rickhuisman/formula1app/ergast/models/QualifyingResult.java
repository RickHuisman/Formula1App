package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.SerializedName;

public class QualifyingResult {

    @SerializedName("number")
    private String number;

    @SerializedName("position")
    private String position;

    @SerializedName("positionText")
    private String positionText;

    @SerializedName("points")
    private String points;

    @SerializedName("Driver")
    private Driver driver;

    @SerializedName("Constructor")
    private Constructor constructor;

    @SerializedName("Q1")
    private String qualifyingOne;

    @SerializedName("Q2")
    private String qualifyingTwo;

    @SerializedName("Q3")
    private String qualifyingThree;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPositionText() {
        return positionText;
    }

    public void setPositionText(String positionText) {
        this.positionText = positionText;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }

    public String getQualifyingOne() {
        return qualifyingOne;
    }

    public void setQualifyingOne(String qualifyingOne) {
        this.qualifyingOne = qualifyingOne;
    }

    public String getQualifyingTwo() {
        return qualifyingTwo;
    }

    public void setQualifyingTwo(String qualifyingTwo) {
        this.qualifyingTwo = qualifyingTwo;
    }

    public String getQualifyingThree() {
        return qualifyingThree;
    }

    public void setQualifyingThree(String qualifyingThree) {
        this.qualifyingThree = qualifyingThree;
    }
}
