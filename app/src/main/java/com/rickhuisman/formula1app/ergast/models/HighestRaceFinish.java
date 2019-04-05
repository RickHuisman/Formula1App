package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HighestRaceFinish {

    @SerializedName("position")
    @Expose
    private String position;

    @SerializedName("amount")
    @Expose
    private String amount;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
