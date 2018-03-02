package com.prem.test.jet.di.module;

import com.prem.test.jet.mvp.model.RoutesListModel;
import com.prem.test.jet.mvp.model.realm.dao.BusRoutesDao;
import com.prem.test.jet.network.ApiInterface;
import com.prem.test.jet.utils.looper.RefreshBusRoutesLooperProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by prem on 01/03/2018.
 */

@Module
public class ModelModule {

    @Provides
    BusRoutesDao provideBusRoutesDao(){
        return new BusRoutesDao();
    }

    @Provides
    @Singleton
    RoutesListModel provideRoutesListModel(ApiInterface apiInterface, BusRoutesDao busRoutesDao, RefreshBusRoutesLooperProvider refreshBusRoutesLooperProvider){
        return new RoutesListModel(apiInterface,busRoutesDao,refreshBusRoutesLooperProvider);
    }


}
