package com.rickhuisman.formula1app.ergast.db.entities;


import androidx.annotation.NonNull;
import androidx.room.Embedded;

public class DriverStandings {

    @NonNull
    @Embedded()
    private DriverStanding driverStanding;

    @NonNull
    @Embedded(prefix = "result_")
    private Result result;

    @NonNull
    @Embedded(prefix = "driver_")
    private Driver driver;

    @NonNull
    @Embedded(prefix = "constructor_")
    private Constructor constructor;

    public DriverStandings(@NonNull DriverStanding driverStanding, @NonNull Result result, @NonNull Driver driver, @NonNull Constructor constructor) {
        this.driverStanding = driverStanding;
        this.result = result;
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
    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(@NonNull Constructor constructor) {
        this.constructor = constructor;
    }

    @Override
    public String toString() {
        return "DriverStandings{" +
                "driverStanding=" + driverStanding +
                ", result=" + result +
                ", driver=" + driver +
                ", constructor=" + constructor +
                '}';
    }
}
