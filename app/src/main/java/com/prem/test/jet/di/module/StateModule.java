package com.prem.test.jet.di.module;

import com.prem.test.jet.mvp.view.binder.RefreshBusRoutesStateBinder;
import com.prem.test.jet.mvp.view.state.RefreshBusRoutesState;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by prem on 28/02/2018.
 */

@Module
public class StateModule {

    @Provides
    @Singleton
    RefreshBusRoutesState provideRefreshBusRoutesState(){
        return new RefreshBusRoutesState();
    }

    @Provides
    @Singleton
    RefreshBusRoutesStateBinder provideRefreshBusRoutesStateBinder(RefreshBusRoutesState refreshBusRoutesState){
        return new RefreshBusRoutesStateBinder(refreshBusRoutesState);
    }

}
