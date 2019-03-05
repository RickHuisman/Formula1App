package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;

public class PodiumCount {

    @ColumnInfo(name = "count")
    private int count;

    public PodiumCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
