package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Embedded;

public class WorldChampionships {

    @NonNull
    @Embedded
    private DriverStanding driverStanding;

    @NonNull
    @Embedded(prefix = "race_")
    private Race race;

    public WorldChampionships(@NonNull DriverStanding driverStanding, @NonNull Race race) {
        this.driverStanding = driverStanding;
        this.race = race;
    }

    @NonNull
    public DriverStanding getDriverStanding() {
        return driverStanding;
    }

    public void setDriverStanding(@NonNull DriverStanding driverStanding) {
        this.driverStanding = driverStanding;
    }

    @NonNull
    public Race getRace() {
        return race;
    }

    public void setRace(@NonNull Race race) {
        this.race = race;
    }
}
