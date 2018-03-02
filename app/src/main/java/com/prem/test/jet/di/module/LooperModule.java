package com.prem.test.jet.di.module;

import com.prem.test.jet.network.NetworkObserver;
import com.prem.test.jet.utils.looper.RefreshBusRoutesLooperProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by prem on 28/02/2018.
 */

@Module
public class LooperModule {

    @Provides
    @Singleton
    RefreshBusRoutesLooperProvider provideRefreshBusRoutesLooperProvider(NetworkObserver networkObserver, CompositeDisposable compositeDisposable) {
        return new RefreshBusRoutesLooperProvider(networkObserver,compositeDisposable);
    }

}
