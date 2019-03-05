package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;

public class HighestRaceFinish {

    @NonNull
    @Embedded
    private Result result;

    @ColumnInfo(name = "count")
    private int count;

    public HighestRaceFinish(@NonNull Result result, int count) {
        this.result = result;
        this.count = count;
    }

    @NonNull
    public Result getResult() {
        return result;
    }

    public void setResult(@NonNull Result result) {
        this.result = result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
