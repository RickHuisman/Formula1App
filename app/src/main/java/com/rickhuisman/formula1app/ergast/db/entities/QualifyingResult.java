package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;

@Entity
public class QualifyingResult {

    @NonNull
    @Embedded(prefix = "qualifying_")
    private Qualifying qualifying;

    @NonNull
    @Embedded(prefix = "driver_")
    private Driver driver;

    public QualifyingResult(@NonNull Qualifying qualifying, @NonNull Driver driver) {
        this.qualifying = qualifying;
        this.driver = driver;
    }

    @NonNull
    public Qualifying getQualifying() {
        return qualifying;
    }

    public void setQualifying(@NonNull Qualifying qualifying) {
        this.qualifying = qualifying;
    }

    @NonNull
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(@NonNull Driver driver) {
        this.driver = driver;
    }
}
