package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.room.ColumnInfo;

public class ConstructorSeason {

    @ColumnInfo(name = "year_start")
    private int start;

    @ColumnInfo(name = "year_end")
    private int end;

    public ConstructorSeason(int start, int end) {
        this.start = start;
        this.end = end;
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
