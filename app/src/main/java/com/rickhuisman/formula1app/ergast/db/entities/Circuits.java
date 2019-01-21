package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "circuits")
public class Circuits {

    @PrimaryKey()
    @ColumnInfo(name = "circuitId")
    private int circuitId;

    @NonNull
    @ColumnInfo(name = "circuitRef")
    private String circuitRef;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "country")
    private String country;

    @ColumnInfo(name = "lat")
    private float date;

    @ColumnInfo(name = "lng")
    private float lng;

    @ColumnInfo(name = "alt")
    private int alt;

    @NonNull
    @ColumnInfo(name = "url")
    private String url;

    public Circuits(int circuitId, @NonNull String circuitRef, @NonNull String name, String location, String country, float date, float lng, int alt, @NonNull String url) {
        this.circuitId = circuitId;
        this.circuitRef = circuitRef;
        this.name = name;
        this.location = location;
        this.country = country;
        this.date = date;
        this.lng = lng;
        this.alt = alt;
        this.url = url;
    }

    public int getCircuitId() {
        return circuitId;
    }

    public void setCircuitId(int circuitId) {
        this.circuitId = circuitId;
    }

    @NonNull
    public String getCircuitRef() {
        return circuitRef;
    }

    public void setCircuitRef(@NonNull String circuitRef) {
        this.circuitRef = circuitRef;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getDate() {
        return date;
    }

    public void setDate(float date) {
        this.date = date;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public int getAlt() {
        return alt;
    }

    public void setAlt(int alt) {
        this.alt = alt;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }
}