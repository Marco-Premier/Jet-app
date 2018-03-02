package com.prem.test.jet.mvp.model.realm.dao.contract;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by prem on 01/03/2018.
 */

public abstract class BaseDao<T,V> {

    public abstract void saveOrUpdate(T data);
    public abstract T list();
    public abstract Observable<T> listDataAsObservable();
    public abstract Observable<V> findByIdAsObservable(String idResource);

    private static Scheduler scheduler;

    protected static Scheduler getScheduler(){
        scheduler = (null == scheduler) ? (AndroidSchedulers.mainThread()) : scheduler;
        return scheduler;
    }

}
