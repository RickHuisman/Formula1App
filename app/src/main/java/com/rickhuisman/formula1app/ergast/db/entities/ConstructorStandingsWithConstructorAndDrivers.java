package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Embedded;

public class ConstructorStandingsWithConstructorAndDrivers {

    @NonNull
    @Embedded
    private ConstructorStandings constructorStandings;

    @NonNull
    @Embedded(prefix = "result_")
    private Results results;

    @NonNull
    @Embedded(prefix = "constructor_")
    private Constructor constructor;

    @NonNull
    @Embedded(prefix = "driver_")
    private Driver driver;

    public ConstructorStandingsWithConstructorAndDrivers(@NonNull ConstructorStandings constructorStandings, @NonNull Results results, @NonNull Constructor constructor, @NonNull Driver driver) {
        this.constructorStandings = constructorStandings;
        this.results = results;
        this.constructor = constructor;
        this.driver = driver;
    }

    @NonNull
    public ConstructorStandings getConstructorStandings() {
        return constructorStandings;
    }

    public void setConstructorStandings(@NonNull ConstructorStandings constructorStandings) {
        this.constructorStandings = constructorStandings;
    }

    @NonNull
    public Results getResults() {
        return results;
    }

    public void setResults(@NonNull Results results) {
        this.results = results;
    }

    @NonNull
    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(@NonNull Constructor constructor) {
        this.constructor = constructor;
    }

    @NonNull
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(@NonNull Driver driver) {
        this.driver = driver;
    }
}
