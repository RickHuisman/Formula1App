package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "constructorResults")
public class ConstructorResult {

    @PrimaryKey
    @ColumnInfo(name = "constructorResultsId")
    private int constructorResultsId;

    @NonNull
    @ColumnInfo(name = "raceId")
    private int raceId;

    @NonNull
    @ColumnInfo(name = "constructorId")
    private int constructorId;

    @ColumnInfo(name = "points")
    private float points;

    @ColumnInfo(name = "status")
    private String status;

    public ConstructorResult(int constructorResultsId, @NonNull int raceId, @NonNull int constructorId, float points, String status) {
        this.constructorResultsId = constructorResultsId;
        this.raceId = raceId;
        this.constructorId = constructorId;
        this.points = points;
        this.status = status;
    }

    public int getConstructorResultsId() {
        return constructorResultsId;
    }

    public void setConstructorResultsId(int constructorResultsId) {
        this.constructorResultsId = constructorResultsId;
    }

    @NonNull
    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(@NonNull int raceId) {
        this.raceId = raceId;
    }

    @NonNull
    public int getConstructorId() {
        return constructorId;
    }

    public void setConstructorId(@NonNull int constructorId) {
        this.constructorId = constructorId;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
