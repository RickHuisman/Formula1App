package com.rickhuisman.formula1app.ergast.db.entities;


import androidx.annotation.NonNull;
import androidx.room.Embedded;

public class DriverStandingsWithDriverAndConstructor {

    @NonNull
    @Embedded()
    private DriverStanding driverStanding;

    @NonNull
    @Embedded(prefix = "result_")
    private Results results;

    @NonNull
    @Embedded(prefix = "driver_")
    private Driver driver;

    @NonNull
    @Embedded(prefix = "constructor_")
    private Constructor constructor;

    public DriverStandingsWithDriverAndConstructor(@NonNull DriverStanding driverStanding, @NonNull Results results, @NonNull Driver driver, @NonNull Constructor constructor) {
        this.driverStanding = driverStanding;
        this.results = results;
        this.driver = driver;
        this.constructor = constructor;
    }

    @NonNull
    public DriverStanding getDriverStanding() {
        return driverStanding;
    }

    public void setDriverStanding(@NonNull DriverStanding driverStanding) {
        this.driverStanding = driverStanding;
    }

    @NonNull
    public Results getResults() {
        return results;
    }

    public void setResults(@NonNull Results results) {
        this.results = results;
    }

    @NonNull
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(@NonNull Driver driver) {
        this.driver = driver;
    }

    @NonNull
    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(@NonNull Constructor constructor) {
        this.constructor = constructor;
    }

    @Override
    public String toString() {
        return "DriverStandingsWithDriverAndConstructor{" +
                "driverStanding=" + driverStanding +
                ", results=" + results +
                ", driver=" + driver +
                ", constructor=" + constructor +
                '}';
    }
}
