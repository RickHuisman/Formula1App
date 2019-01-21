package com.rickhuisman.formula1app.ergast.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "status")
public class Status {

    @PrimaryKey
    @ColumnInfo(name = "statusId")
    private int statusId;

    @NonNull
    @ColumnInfo(name = "status")
    private String status;

    public Status(int statusId, @NonNull String status) {
        this.statusId = statusId;
        this.status = status;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @NonNull
    public String getStatus() {
        return status;
    }

    public void setStatus(@NonNull String status) {
        this.status = status;
    }
}
