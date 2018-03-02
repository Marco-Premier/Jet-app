package com.prem.test.jet.mvp.model.realm;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by prem on 01/03/2018.
 */

public class BusRoute extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private String idBusRoutes;

    @SerializedName("name")
    private String name;

    @SerializedName("stops")
    private RealmList<BusStop> stopsList;

    @SerializedName("description")
    private String description;

    @SerializedName("accessible")
    private String accessible;

    @SerializedName("image")
    private String imageUrl;

    public String getIdBusRoutes() {
        return idBusRoutes;
    }

    public void setIdBusRoutes(String idBusRoutes) {
        this.idBusRoutes = idBusRoutes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<BusStop> getStopsList() {
        return stopsList;
    }

    public void setStopsList(RealmList<BusStop> stopsList) {
        this.stopsList = stopsList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccessible() {
        return accessible;
    }

    public void setAccessible(String accessible) {
        this.accessible = accessible;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
