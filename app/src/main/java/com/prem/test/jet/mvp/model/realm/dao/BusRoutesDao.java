package com.prem.test.jet.mvp.model.realm.dao;

import com.prem.test.jet.mvp.model.realm.BusRoute;
import com.prem.test.jet.mvp.model.realm.BusRoutesWrapper;
import com.prem.test.jet.mvp.model.realm.dao.contract.BaseDao;

import java.util.GregorianCalendar;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposables;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by prem on 01/03/2018.
 */

public class BusRoutesDao extends BaseDao<BusRoutesWrapper,BusRoute> {

    @Override
    public void saveOrUpdate(BusRoutesWrapper data) {

        try(Realm realm = Realm.getDefaultInstance()) {
            realm.beginTransaction();
            RealmResults<BusRoutesWrapper> busRoutesWrapperList = realm.where(BusRoutesWrapper.class)
                    .findAll();
            if(busRoutesWrapperList.size() == 0) {
                data.setIdBusRoutesWrapper(1);
                data.setLastUpdateAt(GregorianCalendar.getInstance().getTime());
                realm.insertOrUpdate(data);
            }else{
                busRoutesWrapperList.get(0).setLastUpdateAt(GregorianCalendar.getInstance().getTime());
                busRoutesWrapperList.get(0).setBusRoutes(data.getBusRoutes());
                realm.insertOrUpdate(busRoutesWrapperList);
            }
            realm.commitTransaction();
        }
    }

    @Override
    public BusRoutesWrapper list() {
        try(Realm realm = Realm.getDefaultInstance()) {
            RealmResults<BusRoutesWrapper> busRoutesWrapperList = realm.where(BusRoutesWrapper.class)
                    .findAll();
            return busRoutesWrapperList.size() > 0 ? busRoutesWrapperList.get(0) : null;
        }
    }

    @Override
    public Observable<BusRoutesWrapper> listDataAsObservable() {
        return Observable.create((ObservableOnSubscribe<BusRoutesWrapper>) emitter -> {
            Realm realm = Realm.getDefaultInstance();
            final RealmResults<BusRoutesWrapper> busRoutesWrapper = realm.where(BusRoutesWrapper.class).equalTo("idBusRoutesWrapper", 1).findAll();
            final RealmChangeListener<RealmResults<BusRoutesWrapper>> realmChangeListener = element -> {
                if(element.isLoaded() && !emitter.isDisposed()) {
                    if(!emitter.isDisposed()) {
                        emitter.onNext(element.first());
                    }
                }
            };
            emitter.setDisposable(Disposables.fromAction(() -> {
                if(busRoutesWrapper.isValid()) {
                    busRoutesWrapper.removeChangeListener(realmChangeListener);
                }
                realm.close();
            }));
            if(busRoutesWrapper.isValid() && busRoutesWrapper.size() > 0) {
                //First set of data
                emitter.onNext(busRoutesWrapper.first());
            }
            busRoutesWrapper.addChangeListener(realmChangeListener);
        })
                .subscribeOn(getScheduler())
                .unsubscribeOn(getScheduler());
    }

    @Override
    public Observable<BusRoute> findByIdAsObservable(final String idResource) {
        return Observable.create((ObservableOnSubscribe<BusRoute>) emitter -> {
            Realm realm = Realm.getDefaultInstance();
            final BusRoute busRoute = realm.where(BusRoute.class).equalTo("idBusRoutes", idResource).findFirst();
            final RealmChangeListener<BusRoute> realmChangeListener = element -> {
                if(element.isLoaded() && !emitter.isDisposed()) {
                    if(!emitter.isDisposed()) {
                        emitter.onNext(element);
                    }
                }
            };
            emitter.setDisposable(Disposables.fromAction(() -> {
                if(busRoute.isValid()) {
                    busRoute.removeChangeListener(realmChangeListener);
                }
                realm.close();
            }));
            if(busRoute.isValid()) {
                //First set of data
                emitter.onNext(busRoute);
            }
            busRoute.addChangeListener(realmChangeListener);
        })
                .subscribeOn(getScheduler())
                .unsubscribeOn(getScheduler());

    }


}
