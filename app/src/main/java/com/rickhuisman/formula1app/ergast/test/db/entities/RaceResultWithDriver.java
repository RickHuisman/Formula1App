package com.rickhuisman.formula1app.ergast.test.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;

@Entity
public class RaceResultWithDriver {

    @NonNull
    @Embedded(prefix = "result_")
    private Results result;

    @NonNull
    @Embedded(prefix = "driver_")
    private Driver driver;

    public RaceResultWithDriver(@NonNull Results result, @NonNull Driver driver) {
        this.result = result;
        this.driver = driver;
    }

    @NonNull
    public Results getResult() {
        return result;
    }

    public void setResult(@NonNull Results result) {
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