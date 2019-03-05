package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.room.ColumnInfo;

public class RaceCount {

    @ColumnInfo(name = "count")
    private int count;

    public RaceCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
