package com.prem.test.jet.mvp.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.prem.test.jet.Jet;
import com.prem.test.jet.mvp.model.RoutesListModel;
import com.prem.test.jet.mvp.model.realm.dao.BusRoutesDao;
import com.prem.test.jet.mvp.view.RoutesListView;
import com.prem.test.jet.mvp.view.binder.RefreshBusRoutesStateBinder;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by prem on 28/02/2018.
 */

public class RoutesListPresenter extends MvpBasePresenter<RoutesListView> {

    @Inject
    RefreshBusRoutesStateBinder refreshBusRoutesStateBinder;

    @Inject
    RoutesListModel routesListModel;

    @Inject
    BusRoutesDao busRoutesDao;

    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    public void attachView(RoutesListView view) {
        super.attachView(view);

        Jet.getDefaultComponent().inject(this);

        //Bind the flow of results coming out from the model into the state binder
        //and finally subscribe on the state itself to get notified when it changes
        disposables.add(refreshBusRoutesStateBinder.bindState(routesListModel.getResultsStream())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(refreshBusRoutesState -> {
                    ifViewAttached(ui -> ui.hideNotificationViews());
                    switch (refreshBusRoutesState.getRequestStatus()){
                        case NO_DATA:
                            ifViewAttached(ui -> ui.showInternetErrorView());
                            break;
                        case IN_FLIGHT:
                            ifViewAttached(ui -> ui.showLoadingView());
                            break;
                        case EMPTY_LIST:
                            ifViewAttached(ui -> ui.showEmptyListView());
                            break;
                        default:
                            ifViewAttached(ui -> ui.hideNotificationViews());
                            break;
                    }
                }));

        //Subscribe on data coming from realm DB
        disposables.add(busRoutesDao.listDataAsObservable()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(busRoutesWrapper -> {
                    ifViewAttached(ui -> ui.refreshListData(busRoutesWrapper.getBusRoutes()));
                }));
    }

    @Override
    public void detachView(){
        super.detachView();

        disposables.clear();

    }

}
