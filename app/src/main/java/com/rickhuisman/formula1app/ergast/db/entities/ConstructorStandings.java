package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "constructorStandings")
public class ConstructorStandings {

    @PrimaryKey
    @ColumnInfo(name = "constructorStandingsId")
    private int constructorStandingsId;

    @NonNull
    @ColumnInfo(name = "raceId")
    private int raceId;

    @NonNull
    @ColumnInfo(name = "constructorId")
    private int constructorId;

    @NonNull
    @ColumnInfo(name = "points")
    private float points;

    @ColumnInfo(name = "position")
    private int position;

    @ColumnInfo(name = "positionText")
    private String positionText;

    @NonNull
    @ColumnInfo(name = "wins")
    private int wins;

    public ConstructorStandings(int constructorStandingsId, @NonNull int raceId, @NonNull int constructorId, @NonNull float points, int position, String positionText, @NonNull int wins) {
        this.constructorStandingsId = constructorStandingsId;
        this.raceId = raceId;
        this.constructorId = constructorId;
        this.points = points;
        this.position = position;
        this.positionText = positionText;
        this.wins = wins;
    }

    public int getConstructorStandingsId() {
        return constructorStandingsId;
    }

    public void setConstructorStandingsId(int constructorStandingsId) {
        this.constructorStandingsId = constructorStandingsId;
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

    @NonNull
    public float getPoints() {
        return points;
    }

    public void setPoints(@NonNull float points) {
        this.points = points;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPositionText() {
        return positionText;
    }

    public void setPositionText(String positionText) {
        this.positionText = positionText;
    }

    @NonNull
    public int getWins() {
        return wins;
    }

    public void setWins(@NonNull int wins) {
        this.wins = wins;
    }
}