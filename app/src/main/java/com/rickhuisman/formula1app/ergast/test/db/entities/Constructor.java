package com.rickhuisman.formula1app.ergast.test.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "constructors")
public class Constructor {

    @PrimaryKey()
    @ColumnInfo(name = "constructorId")
    private int constructorId;

    @NonNull
    @ColumnInfo(name = "constructorRef")
    private String constructorRef;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "nationality")
    private String nationality;

    @NonNull
    @ColumnInfo(name = "url")
    private String url;

    public Constructor(int constructorId, @NonNull String constructorRef, @NonNull String name, String nationality, @NonNull String url) {
        this.constructorId = constructorId;
        this.constructorRef = constructorRef;
        this.name = name;
        this.nationality = nationality;
        this.url = url;
    }

    public int getConstructorId() {
        return constructorId;
    }

    public void setConstructorId(int constructorId) {
        this.constructorId = constructorId;
    }

    @NonNull
    public String getConstructorRef() {
        return constructorRef;
    }

    public void setConstructorRef(@NonNull String constructorRef) {
        this.constructorRef = constructorRef;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Constructor{" +
                "constructorId=" + constructorId +
                ", constructorRef='" + constructorRef + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
