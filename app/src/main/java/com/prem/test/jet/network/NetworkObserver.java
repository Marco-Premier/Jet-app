package com.prem.test.jet.network;

import android.content.Context;

import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.prem.test.jet.mvp.model.action.RefreshBusRoutesAction;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by prem on 02/03/2018.
 */

public class NetworkObserver {

    private final Context context;

    public NetworkObserver(Context context){
        this.context = context;
    }

    public Observable<Connectivity> getConnectivityObservable(final BehaviorRelay<RefreshBusRoutesAction> refreshBusRuoutsActionProvider){

        return ReactiveNetwork.observeNetworkConnectivity(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
