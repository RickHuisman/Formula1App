package com.rickhuisman.formula1app.ergast.test.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "races")
public class Races {

    @PrimaryKey()
    @ColumnInfo(name = "raceId")
    private int raceId;

    @ColumnInfo(name = "year")
    private int year;

    @ColumnInfo(name = "round")
    private int round;

    @ColumnInfo(name = "circuitId")
    private int circuitId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "url")
    private String url;

    public Races(int raceId, int year, int round, int circuitId, String name, String date, String time, String url) {
        this.raceId = raceId;
        this.year = year;
        this.round = round;
        this.circuitId = circuitId;
        this.name = name;
        this.date = date;
        this.time = time;
        this.url = url;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getCircuitId() {
        return circuitId;
    }

    public void setCircuitId(int circuitId) {
        this.circuitId = circuitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Races{" +
                "raceId=" + raceId +
                ", year=" + year +
                ", round=" + round +
                ", circuitId=" + circuitId +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
