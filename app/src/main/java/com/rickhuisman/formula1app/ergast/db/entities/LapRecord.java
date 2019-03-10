package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Embedded;

public class LapRecord {

    @NonNull
    @Embedded(prefix = "driver_")
    private Driver driver;

    @NonNull
    @Embedded()
    private LapTimes lapTime;

    public LapRecord(@NonNull Driver driver, @NonNull LapTimes lapTime) {
        this.driver = driver;
        this.lapTime = lapTime;
    }

    @NonNull
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(@NonNull Driver driver) {
        this.driver = driver;
    }

    @NonNull
    public LapTimes getLapTime() {
        return lapTime;
    }

    public void setLapTime(@NonNull LapTimes lapTime) {
        this.lapTime = lapTime;
    }
}
