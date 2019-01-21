package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Embedded;

public class DriverStandingsWithDriver {

    @NonNull
    @Embedded
    private DriverStanding driverStanding;

    @NonNull
    @Embedded(prefix = "driver_")
    private Driver driver;

    public DriverStandingsWithDriver(@NonNull DriverStanding driverStanding, @NonNull Driver driver) {
        this.driverStanding = driverStanding;
        this.driver = driver;
    }

    @NonNull
    public DriverStanding getDriverStanding() {
        return driverStanding;
    }

    public void setDriverStanding(@NonNull DriverStanding driverStanding) {
        this.driverStanding = driverStanding;
    }

    @NonNull
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(@NonNull Driver driver) {
        this.driver = driver;
    }
}
