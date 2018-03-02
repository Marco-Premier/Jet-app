package com.prem.test.jet.mvp.model;

import com.prem.test.jet.mvp.model.action.RefreshBusRoutesAction;
import com.prem.test.jet.mvp.model.action.contract.BaseAction;
import com.prem.test.jet.mvp.model.contract.BaseModel;
import com.prem.test.jet.mvp.model.realm.dao.BusRoutesDao;
import com.prem.test.jet.mvp.model.result.RefreshBusRoutesResult;
import com.prem.test.jet.mvp.model.result.contract.BaseResult;
import com.prem.test.jet.network.ApiInterface;
import com.prem.test.jet.utils.looper.RefreshBusRoutesLooperProvider;
import com.prem.test.jet.utils.looper.contract.BaseLooper;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by prem on 28/02/2018.
 */

public class RoutesListModel extends BaseModel{

    private final ApiInterface apiInterface;
    private final BusRoutesDao busRoutesDao;
    private final BaseLooper<RefreshBusRoutesAction> refreshBusRoutesLooperProvider;

    private ObservableTransformer<BaseAction,BaseResult> refreshBusRoutesTransformer;

    public RoutesListModel(ApiInterface apiInterface, BusRoutesDao busRoutesDao, BaseLooper refreshBusRoutesLooperProvider){
        this.apiInterface = apiInterface;
        this.busRoutesDao = busRoutesDao;
        this.refreshBusRoutesLooperProvider = refreshBusRoutesLooperProvider;
        setupResultsStream();
    }

    /**
     * Setup transformers to convert a stream of actions into a stream of results
     */
    private void setupResultsStream(){

        //Fire API request and save data into the DB.
        //Views are observing the DB so they will get notified
        //when new data is available
        refreshBusRoutesTransformer =
                actions -> actions
                        .flatMap(action -> apiInterface.refreshBusRoutes()
                                .map(busRoutesWrapper -> {
                                    busRoutesDao.saveOrUpdate(busRoutesWrapper);
                                    ((RefreshBusRoutesLooperProvider)refreshBusRoutesLooperProvider).setWaitingForData(false);
                                    return (busRoutesWrapper.getBusRoutes().size() == 0) ? RefreshBusRoutesResult.EMPTY_LIST() : RefreshBusRoutesResult.SUCCESS();
                                })
                                .onErrorReturn(e -> RefreshBusRoutesResult.FAILURE())
                                .observeOn(AndroidSchedulers.mainThread())
                                .startWith(RefreshBusRoutesResult.IN_FLIGHT()));


        //Connect the stream of actions to the transformer
        resultsStream = refreshBusRoutesLooperProvider
                .getLooper()
                .compose(refreshBusRoutesTransformer);
    }

}
