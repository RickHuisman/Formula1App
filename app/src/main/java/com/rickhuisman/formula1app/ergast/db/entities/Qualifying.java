package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "qualifying")
public class Qualifying {

    @PrimaryKey
    @ColumnInfo(name = "qualifyId")
    private int qualifyId;

    @ColumnInfo(name = "raceId")
    private int raceId;

    @ColumnInfo(name = "driverId")
    private int driverId;

    @ColumnInfo(name = "constructorId")
    private int constructorId;

    @ColumnInfo(name = "number")
    private int number;

    @ColumnInfo(name = "position")
    private int position;

    @ColumnInfo(name = "q1")
    private String qualifyingOne;

    @ColumnInfo(name = "q2")
    private String qualifyingTwo;

    @ColumnInfo(name = "q3")
    private String qualifyingThree;

    public Qualifying(int qualifyId, int raceId, int driverId, int constructorId, int number, int position, String qualifyingOne, String qualifyingTwo, String qualifyingThree) {
        this.qualifyId = qualifyId;
        this.raceId = raceId;
        this.driverId = driverId;
        this.constructorId = constructorId;
        this.number = number;
        this.position = position;
        this.qualifyingOne = qualifyingOne;
        this.qualifyingTwo = qualifyingTwo;
        this.qualifyingThree = qualifyingThree;
    }

    public int getQualifyId() {
        return qualifyId;
    }

    public void setQualifyId(int qualifyId) {
        this.qualifyId = qualifyId;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getConstructorId() {
        return constructorId;
    }

    public void setConstructorId(int constructorId) {
        this.constructorId = constructorId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
