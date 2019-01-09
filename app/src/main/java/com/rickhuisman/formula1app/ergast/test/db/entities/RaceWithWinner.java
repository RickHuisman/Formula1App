package com.rickhuisman.formula1app.ergast.test.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;

@Entity
public class RaceWithWinner {

    @NonNull
    @Embedded(prefix = "race_")
    private Races race;

    @NonNull
    @Embedded(prefix = "result_")
    private Results result;

    @NonNull
    @Embedded(prefix = "constructor_")
    private Constructor constructor;

    @NonNull
    @Embedded(prefix = "circuit_")
    private Circuits circuit;

    public RaceWithWinner(@NonNull Races race, @NonNull Results result, @NonNull Constructor constructor, @NonNull Circuits circuit) {
        this.race = race;
        this.result = result;
        this.constructor = constructor;
        this.circuit = circuit;
    }

    @NonNull
    public Races getRace() {
        return race;
    }

    public void setRace(@NonNull Races race) {
        this.race = race;
    }

    @NonNull
    public Results getResult() {
        return result;
    }

    public void setResult(@NonNull Results result) {
        this.result = result;
    }

    @NonNull
    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(@NonNull Constructor constructor) {
        this.constructor = constructor;
    }

    @NonNull
    public Circuits getCircuit() {
        return circuit;
    }

    public void setCircuit(@NonNull Circuits circuit) {
        this.circuit = circuit;
    }
}
