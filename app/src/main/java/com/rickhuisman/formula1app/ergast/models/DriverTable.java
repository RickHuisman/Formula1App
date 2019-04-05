package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DriverTable {

    @SerializedName("driverId")
    @Expose
    private String driverId;

    @SerializedName("Drivers")
    @Expose
    private ArrayList<Driver> drivers;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(ArrayList<Driver> drivers) {
        this.drivers = drivers;
    }
}
