package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Embedded;

public class ConstructorStandingsWithConstructor {

    @NonNull
    @Embedded
    private ConstructorStandings constructorStandings;

    @NonNull
    @Embedded(prefix = "constructor_")
    private Constructor constructor;

    public ConstructorStandingsWithConstructor(@NonNull ConstructorStandings constructorStandings, @NonNull Constructor constructor) {
        this.constructorStandings = constructorStandings;
        this.constructor = constructor;
    }

    @NonNull
    public ConstructorStandings getConstructorStandings() {
        return constructorStandings;
    }

    public void setConstructorStandings(@NonNull ConstructorStandings constructorStandings) {
        this.constructorStandings = constructorStandings;
    }

    @NonNull
    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(@NonNull Constructor constructor) {
        this.constructor = constructor;
    }
}
