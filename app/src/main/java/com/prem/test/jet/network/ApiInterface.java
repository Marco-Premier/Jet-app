package com.prem.test.jet.network;

import com.prem.test.jet.mvp.model.realm.BusRoutesWrapper;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by prem on 28/02/2018.
 */

public interface ApiInterface {

    @GET("5808f00d10000005074c6340")
    Observable<BusRoutesWrapper> refreshBusRoutes();

}