package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;

@Entity
public class RaceWithWinner {

    @NonNull
    @Embedded(prefix = "race_")
    private Race race;

    @NonNull
    @Embedded(prefix = "result_")
    private Result result;

    @NonNull
    @Embedded(prefix = "constructor_")
    private Constructor constructor;

    @NonNull
    @Embedded(prefix = "circuit_")
    private Circuit circuit;

    public RaceWithWinner(@NonNull Race race, @NonNull Result result, @NonNull Constructor constructor, @NonNull Circuit circuit) {
        this.race = race;
        this.result = result;
        this.constructor = constructor;
        this.circuit = circuit;
    }

    @NonNull
    public Race getRace() {
        return race;
    }

    public void setRace(@NonNull Race race) {
        this.race = race;
    }

    @NonNull
    public Result getResult() {
        return result;
    }

    public void setResult(@NonNull Result result) {
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
    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(@NonNull Circuit circuit) {
        this.circuit = circuit;
    }
}
