package com.prem.test.jet.utils.looper.contract;


import com.prem.test.jet.mvp.model.action.contract.BaseAction;

import io.reactivex.Observable;

/**
 * Created by prem on 28/02/2018.
 */

public interface BaseLooper<T extends BaseAction> {

    Observable<T> getLooper();

}
