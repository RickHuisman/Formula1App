package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Embedded;

public class ConstructorWorldChampionship {

    @NonNull
    @Embedded
    private ConstructorStandings constructorStandings;

    @NonNull
    @Embedded(prefix = "race_")
    private Race race;

    public ConstructorWorldChampionship(@NonNull ConstructorStandings constructorStandings, @NonNull Race race) {
        this.constructorStandings = constructorStandings;
        this.race = race;
    }

    @NonNull
    public ConstructorStandings getConstructorStandings() {
        return constructorStandings;
    }

    public void setConstructorStandings(@NonNull ConstructorStandings constructorStandings) {
        this.constructorStandings = constructorStandings;
    }

    @NonNull
    public Race getRace() {
        return race;
    }

    public void setRace(@NonNull Race race) {
        this.race = race;
    }
}
