package com.rickhuisman.formula1app.ergast.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Seasons {

    @SerializedName("seasonFirst")
    @Expose
    private String seasonFirst;

    @SerializedName("seasonLast")
    @Expose
    private String seasonLast;

    public String getSeasonFirst() {
        return seasonFirst;
    }

    public void setSeasonFirst(String seasonFirst) {
        this.seasonFirst = seasonFirst;
    }

    public String getSeasonLast() {
        return seasonLast;
    }

    public void setSeasonLast(String seasonLast) {
        this.seasonLast = seasonLast;
    }
}
