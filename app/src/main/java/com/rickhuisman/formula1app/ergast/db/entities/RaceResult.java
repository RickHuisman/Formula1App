package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;

@Entity
public class RaceResult {

    @NonNull
    @Embedded(prefix = "result_")
    private Result result;

    @NonNull
    @Embedded(prefix = "driver_")
    private Driver driver;

    @NonNull
    @Embedded(prefix = "status_")
    private Status status;

    public RaceResult(@NonNull Result result, @NonNull Driver driver, @NonNull Status status) {
        this.result = result;
        this.driver = driver;
        this.status = status;
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

    @NonNull
    public Status getStatus() {
        return status;
    }

    public void setStatus(@NonNull Status status) {
        this.status = status;
    }
}