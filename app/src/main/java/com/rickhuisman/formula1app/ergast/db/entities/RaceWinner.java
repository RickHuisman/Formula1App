package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Embedded;

public class RaceWinner {

    @NonNull
    @Embedded(prefix = "result_")
    private Result result;

    @NonNull
    @Embedded(prefix = "driver_")
    private Driver driver;

    public RaceWinner(@NonNull Result result, @NonNull Driver driver) {
        this.result = result;
        this.driver = driver;
    }

    @NonNull
    public Result getResult() {
        return result;
    }

    public void setResult(@NonNull Result result) {
        this.result = result;
    }

    @NonNull
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(@NonNull Driver driver) {
        this.driver = driver;
    }
}
