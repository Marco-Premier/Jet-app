package com.prem.test.jet.utils.looper;

import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.prem.test.jet.mvp.model.action.RefreshBusRoutesAction;
import com.prem.test.jet.network.NetworkObserver;
import com.prem.test.jet.utils.looper.contract.BaseLooper;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by prem on 28/02/2018.
 * This class is responsible for providing a stream of {@link RefreshBusRoutesAction}.
 * It contains logic that emits new action each 30 minutes (in order to refresh data).
 * Also, this class uses {@link NetworkObserver} to monitor network connectivity.
 * If connection becomes available and data has not been retrieved yet, a new action
 * is fired straight away.
 */

public class RefreshBusRoutesLooperProvider implements BaseLooper<RefreshBusRoutesAction>{

    private final NetworkObserver networkObserver;
    private final CompositeDisposable disposables;
    private final BehaviorRelay<RefreshBusRoutesAction> refreshBusRuoutsActionProvider;
    private boolean isWaitingForData = true;
    private boolean isSetup = false;

    public RefreshBusRoutesLooperProvider(NetworkObserver networkObserver, CompositeDisposable disposables){
        this.networkObserver = networkObserver;
        this.disposables = disposables;
        this.refreshBusRuoutsActionProvider = BehaviorRelay.create();
    }

    @Override
    public Observable<RefreshBusRoutesAction> getLooper (){

        if(!isSetup)
            setupObservable();

        return refreshBusRuoutsActionProvider;

    }

    public void setWaitingForData(boolean waitingForData) {
        isWaitingForData = waitingForData;
    }

    private void setupObservable(){

        disposables.add(networkObserver
                .getConnectivityObservable(refreshBusRuoutsActionProvider)
                .subscribe(new Consumer<Connectivity>() {
                    @Override public void accept(final Connectivity connectivity) {
                        if(connectivity.isAvailable() && isWaitingForData)
                            refreshBusRuoutsActionProvider.accept(new RefreshBusRoutesAction());
                    }
                }));

        disposables.add(Observable
                .interval(30, TimeUnit.MINUTES)
                .startWith(1L)
                .doOnNext(signal -> refreshBusRuoutsActionProvider.accept(new RefreshBusRoutesAction()))
                .subscribe());

        isSetup = true;
    }

    @Override
    public void finalize(){
        disposables.clear();
    }

}
