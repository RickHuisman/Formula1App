package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "results")
public class Results {

    @PrimaryKey()
    @ColumnInfo(name = "resultId")
    private int resultId;

    @NonNull
    @ColumnInfo(name = "raceId")
    private int raceId;

    @NonNull
    @ColumnInfo(name = "driverId")
    private int driverId;

    @NonNull
    @ColumnInfo(name = "constructorId")
    private int constructorId;

    @ColumnInfo(name = "number")
    private int number;

    @NonNull
    @ColumnInfo(name = "grid")
    private int grid;

    @ColumnInfo(name = "position")
    private int position;

    @NonNull
    @ColumnInfo(name = "positionText")
    private String positionText;

    @NonNull
    @ColumnInfo(name = "positionOrder")
    private int positionOrder;

    @NonNull
    @ColumnInfo(name = "points")
    private float points;

    @NonNull
    @ColumnInfo(name = "laps")
    private int laps;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "milliseconds")
    private int milliseconds;

    @ColumnInfo(name = "fastestLap")
    private int fastestLap;

    @ColumnInfo(name = "rank")
    private int rank;

    @ColumnInfo(name = "fastestLapTime")
    private String fastestLapTime;

    @ColumnInfo(name = "fastestLapSpeed")
    private String fastestLapSpeed;

    @NonNull
    @ColumnInfo(name = "statusId")
    private int statusId;

    public Results(int resultId, @NonNull int raceId, @NonNull int driverId, @NonNull int constructorId, int number, @NonNull int grid, int position, @NonNull String positionText, @NonNull int positionOrder, @NonNull float points, @NonNull int laps, String time, int milliseconds, int fastestLap, int rank, String fastestLapTime, String fastestLapSpeed, @NonNull int statusId) {
        this.resultId = resultId;
        this.raceId = raceId;
        this.driverId = driverId;
        this.constructorId = constructorId;
        this.number = number;
        this.grid = grid;
        this.position = position;
        this.positionText = positionText;
        this.positionOrder = positionOrder;
        this.points = points;
        this.laps = laps;
        this.time = time;
        this.milliseconds = milliseconds;
        this.fastestLap = fastestLap;
        this.rank = rank;
        this.fastestLapTime = fastestLapTime;
        this.fastestLapSpeed = fastestLapSpeed;
        this.statusId = statusId;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    @NonNull
    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(@NonNull int raceId) {
        this.raceId = raceId;
    }

    @NonNull
    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(@NonNull int driverId) {
        this.driverId = driverId;
    }

    @NonNull
    public int getConstructorId() {
        return constructorId;
    }

    public void setConstructorId(@NonNull int constructorId) {
        this.constructorId = constructorId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @NonNull
    public int getGrid() {
        return grid;
    }

    public void setGrid(@NonNull int grid) {
        this.grid = grid;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @NonNull
    public String getPositionText() {
        return positionText;
    }

    public void setPositionText(@NonNull String positionText) {
        this.positionText = positionText;
    }

    @NonNull
    public int getPositionOrder() {
        return positionOrder;
    }

    public void setPositionOrder(@NonNull int positionOrder) {
        this.positionOrder = positionOrder;
    }

    @NonNull
    public float getPoints() {
        return points;
    }

    public void setPoints(@NonNull float points) {
        this.points = points;
    }

    @NonNull
    public int getLaps() {
        return laps;
    }

    public void setLaps(@NonNull int laps) {
        this.laps = laps;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public int getFastestLap() {
        return fastestLap;
    }

    public void setFastestLap(int fastestLap) {
        this.fastestLap = fastestLap;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getFastestLapTime() {
        return fastestLapTime;
    }

    public void setFastestLapTime(String fastestLapTime) {
        this.fastestLapTime = fastestLapTime;
    }

    public String getFastestLapSpeed() {
        return fastestLapSpeed;
    }

    public void setFastestLapSpeed(String fastestLapSpeed) {
        this.fastestLapSpeed = fastestLapSpeed;
    }

    @NonNull
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(@NonNull int statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "Results{" +
                "resultId=" + resultId +
                ", raceId=" + raceId +
                ", driverId=" + driverId +
                ", constructorId=" + constructorId +
                ", number=" + number +
                ", grid=" + grid +
                ", position=" + position +
                ", positionText='" + positionText + '\'' +
                ", positionOrder=" + positionOrder +
                ", points=" + points +
                ", laps=" + laps +
                ", time='" + time + '\'' +
                ", milliseconds=" + milliseconds +
                ", fastestLap=" + fastestLap +
                ", rank=" + rank +
                ", fastestLapTime='" + fastestLapTime + '\'' +
                ", fastestLapSpeed='" + fastestLapSpeed + '\'' +
                ", statusId=" + statusId +
                '}';
    }
}