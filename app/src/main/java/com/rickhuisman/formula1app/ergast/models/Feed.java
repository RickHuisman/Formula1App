package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.SerializedName;

public class Feed {

    @SerializedName("MRData")
    private MRData mrData;

    public MRData getMrData() {
        return mrData;
    }

    public void setMrData(MRData mrData) {
        this.mrData = mrData;
    }
}