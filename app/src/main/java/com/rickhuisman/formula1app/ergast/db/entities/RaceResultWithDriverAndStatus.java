package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;

@Entity
public class RaceResultWithDriverAndStatus {

    @NonNull
    @Embedded(prefix = "result_")
    private Results result;

    @NonNull
    @Embedded(prefix = "driver_")
    private Driver driver;

    @NonNull
    @Embedded(prefix = "status_")
    private Status status;

    public RaceResultWithDriverAndStatus(@NonNull Results result, @NonNull Driver driver, @NonNull Status status) {
        this.result = result;
        this.driver = driver;
        this.status = status;
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

    @NonNull
    public Status getStatus() {
        return status;
    }

    public void setStatus(@NonNull Status status) {
        this.status = status;
    }
}