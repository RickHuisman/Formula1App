package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ConstructorTable {

    @SerializedName("constructorId")
    @Expose
    private String constructorId;

    @SerializedName("Constructors")
    @Expose
    private ArrayList<Constructor> constructors;

    public String getConstructorId() {
        return constructorId;
    }

    public void setConstructorId(String constructorId) {
        this.constructorId = constructorId;
    }

    public ArrayList<Constructor> getConstructors() {
        return constructors;
    }

    public void setConstructors(ArrayList<Constructor> constructors) {
        this.constructors = constructors;
    }
}
