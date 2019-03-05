package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;

public class CircuitAndFirstGP {

    @NonNull
    @Embedded
    private Circuit circuit;

    @ColumnInfo(name = "year")
    private int year;

    public CircuitAndFirstGP(@NonNull Circuit circuit, int year) {
        this.circuit = circuit;
        this.year = year;
    }

    @NonNull
    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(@NonNull Circuit circuit) {
        this.circuit = circuit;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
