package com.prem.test.jet.mvp.view.binder;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.prem.test.jet.mvp.model.result.RefreshBusRoutesResult;
import com.prem.test.jet.mvp.model.result.contract.BaseResult;
import com.prem.test.jet.mvp.view.binder.contract.BaseBinder;
import com.prem.test.jet.mvp.view.state.RefreshBusRoutesState;
import com.prem.test.jet.mvp.view.state.common.REQUEST_STATUS;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by prem on 28/02/2018.
 * This class binds the stream of {@link BaseResult} coming from {@link com.prem.test.jet.mvp.model.RoutesListModel} into {@link RefreshBusRoutesState}.
 */

public class RefreshBusRoutesStateBinder extends BaseBinder<RefreshBusRoutesState> {

    private final RefreshBusRoutesState refreshBusRoutesState;

    public RefreshBusRoutesStateBinder(RefreshBusRoutesState refreshBusRoutesState){
        this.refreshBusRoutesState = refreshBusRoutesState;
        observableState = BehaviorRelay.createDefault(refreshBusRoutesState);
    }

    /**
     * @param results
     * @return the state as observable so that, every time it changes, the presenter will be notified
     */
    @Override
    public Observable<RefreshBusRoutesState> bindState(Observable<BaseResult> results) {

        Disposable disposable = results.doOnNext(result -> {
            if (result instanceof RefreshBusRoutesResult) {

                switch (((RefreshBusRoutesResult)result).getRequestStatus()){
                    case IN_FLIGHT:
                        refreshBusRoutesState.setRequestStatus(REQUEST_STATUS.IN_FLIGHT);
                        break;
                    case SUCCESS:
                        refreshBusRoutesState.setRequestStatus(REQUEST_STATUS.SUCCESS);
                        break;
                    case NO_DATA:
                        if(refreshBusRoutesState.isWaitingForData())
                            refreshBusRoutesState.setRequestStatus(REQUEST_STATUS.NO_DATA);
                        else
                            refreshBusRoutesState.setRequestStatus(REQUEST_STATUS.SUCCESS);
                        break;
                    case IDLE:
                        refreshBusRoutesState.setRequestStatus(REQUEST_STATUS.IDLE);
                        break;
                }

                observableState.accept(refreshBusRoutesState);
            }
        }).subscribe();

        disposables.add(disposable);

        return observableState;

    }

    @Override
    public void finalize(){
        disposables.clear();
    }

}
