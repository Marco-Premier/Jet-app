package com.prem.test.jet.mvp.model.realm;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by prem on 01/03/2018.
 */

public class BusRoutesWrapper extends RealmObject {

    @PrimaryKey
    private long idBusRoutesWrapper;

    private Date lastUpdateAt;

    @SerializedName("routes")
    private RealmList<BusRoute> busRoutes;

    public long getIdBusRoutesWrapper() {
        return idBusRoutesWrapper;
    }

    public void setIdBusRoutesWrapper(long idBusRoutesWrapper) {
        this.idBusRoutesWrapper = idBusRoutesWrapper;
    }

    public Date getLastUpdateAt() {
        return lastUpdateAt;
    }

    public void setLastUpdateAt(Date lastUpdateAt) {
        this.lastUpdateAt = lastUpdateAt;
    }

    public RealmList<BusRoute> getBusRoutes() {
        return busRoutes;
    }

    public void setBusRoutes(RealmList<BusRoute> busRoutes) {
        this.busRoutes = busRoutes;
    }
}
