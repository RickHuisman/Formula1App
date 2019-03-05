package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;

public class Team {

    @NonNull
    @Embedded
    private Constructor constructor;

    @ColumnInfo(name = "year_start")
    private int start;

    @ColumnInfo(name = "year_end")
    private int end;

    public Team(@NonNull Constructor constructor, int start, int end) {
        this.constructor = constructor;
        this.start = start;
        this.end = end;
    }

    @NonNull
    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(@NonNull Constructor constructor) {
        this.constructor = constructor;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
