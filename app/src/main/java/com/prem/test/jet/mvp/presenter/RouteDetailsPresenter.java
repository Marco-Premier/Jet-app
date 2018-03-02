package com.prem.test.jet.mvp.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.prem.test.jet.Jet;
import com.prem.test.jet.mvp.model.realm.dao.BusRoutesDao;
import com.prem.test.jet.mvp.view.RouteDetailsView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by prem on 01/03/2018.
 */

public class RouteDetailsPresenter extends MvpBasePresenter<RouteDetailsView> {

    @Inject
    BusRoutesDao busRoutesDao;

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final String idBusRoute;

    public RouteDetailsPresenter(String idBusRoute){
        this.idBusRoute = idBusRoute;
    }

    @Override
    public void attachView(RouteDetailsView view) {
        super.attachView(view);

        Jet.getDefaultComponent().inject(this);

        //Observe DB to get the list of stops
        disposables.add(busRoutesDao.findByIdAsObservable(idBusRoute)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(busRoute -> {
                    if(Boolean.parseBoolean(busRoute.getAccessible())){
                        ifViewAttached(ui -> ui.showAccessibleIcon());
                    }else{
                        ifViewAttached(ui -> ui.hideAccessibleIcon());
                    }
                    ifViewAttached(ui -> ui.refreshListData(busRoute.getStopsList()));
                }));
    }

    @Override
    public void detachView(){
        super.detachView();

        disposables.clear();

    }

}
