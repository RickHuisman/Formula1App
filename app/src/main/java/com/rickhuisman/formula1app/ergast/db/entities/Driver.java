package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "drivers")
public class Driver {

    @PrimaryKey()
    @ColumnInfo(name = "driverId")
    private int driverId;

    @ColumnInfo(name = "driverRef")
    private String driverRef;

    @ColumnInfo(name = "number")
    private int number;

    @ColumnInfo(name = "code")
    private String code;

    @ColumnInfo(name = "forename")
    private String foreName;

    @ColumnInfo(name = "surname")
    private String surName;

    @ColumnInfo(name = "dob")
    private String dob;

    @ColumnInfo(name = "nationality")
    private String nationality;

    @ColumnInfo(name = "url")
    private String url;

    public Driver(int driverId, String driverRef, int number, String code, String foreName, String surName, String dob, String nationality, String url) {
        this.driverId = driverId;
        this.driverRef = driverRef;
        this.number = number;
        this.code = code;
        this.foreName = foreName;
        this.surName = surName;
        this.dob = dob;
        this.nationality = nationality;
        this.url = url;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getDriverRef() {
        return driverRef;
    }

    public void setDriverRef(String driverRef) {
        this.driverRef = driverRef;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getForeName() {
        return foreName;
    }

    public void setForeName(String foreName) {
        this.foreName = foreName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
