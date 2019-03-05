package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;

public class HighestGrid {

    @NonNull
    @Embedded()
    private Qualifying qualifying;

    @ColumnInfo(name = "count")
    private int count;

    public HighestGrid(@NonNull Qualifying qualifying, int count) {
        this.qualifying = qualifying;
        this.count = count;
    }

    @NonNull
    public Qualifying getQualifying() {
        return qualifying;
    }

    public void setQualifying(@NonNull Qualifying qualifying) {
        this.qualifying = qualifying;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
