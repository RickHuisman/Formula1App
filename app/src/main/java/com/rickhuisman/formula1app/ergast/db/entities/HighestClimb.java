package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;

public class HighestClimb {

    @NonNull
    @Embedded()
    private Driver driver;

    @ColumnInfo(name = "climb")
    private int climb;

    public HighestClimb(@NonNull Driver driver, int climb) {
        this.driver = driver;
        this.climb = climb;
    }

    @NonNull
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(@NonNull Driver driver) {
        this.driver = driver;
    }

    public int getClimb() {
        return climb;
    }

    public void setClimb(int climb) {
        this.climb = climb;
    }
}
