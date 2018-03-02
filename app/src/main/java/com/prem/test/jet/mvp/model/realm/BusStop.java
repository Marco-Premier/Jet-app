package com.prem.test.jet.mvp.model.realm;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by prem on 01/03/2018.
 */

public class BusStop extends RealmObject {

    @SerializedName("name")
    private String stopName;

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }
}
