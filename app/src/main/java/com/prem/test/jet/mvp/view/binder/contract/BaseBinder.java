package com.prem.test.jet.mvp.view.binder.contract;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.prem.test.jet.mvp.model.result.contract.BaseResult;
import com.prem.test.jet.mvp.view.state.contract.BaseState;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by prem on 28/02/2018.
 */

public abstract class BaseBinder<T extends BaseState> {

    protected final CompositeDisposable disposables = new CompositeDisposable();

    protected BehaviorRelay<T> observableState;

    public abstract Observable<T> bindState(Observable<BaseResult> results);

}
