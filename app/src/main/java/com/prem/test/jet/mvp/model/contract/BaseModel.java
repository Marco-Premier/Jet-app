package com.prem.test.jet.mvp.model.contract;

import com.prem.test.jet.mvp.model.result.contract.BaseResult;

import io.reactivex.Observable;

/**
 * Created by prem on 28/02/2018.
 */

public abstract class BaseModel {

    protected Observable<BaseResult> resultsStream;

    public Observable<BaseResult> getResultsStream(){
        return resultsStream;
    }

}
