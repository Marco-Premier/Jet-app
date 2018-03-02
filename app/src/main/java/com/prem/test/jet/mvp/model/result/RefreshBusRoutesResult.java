package com.prem.test.jet.mvp.model.result;

import com.prem.test.jet.mvp.model.result.contract.BaseResult;
import com.prem.test.jet.mvp.view.state.common.REQUEST_STATUS;

/**
 * Created by prem on 28/02/2018.
 */

public class RefreshBusRoutesResult extends BaseResult {

    private final REQUEST_STATUS requestStatus;

    private RefreshBusRoutesResult(REQUEST_STATUS requestStatus){
        this.requestStatus = requestStatus;
    }

    public static RefreshBusRoutesResult IN_FLIGHT(){
        return new RefreshBusRoutesResult(REQUEST_STATUS.IN_FLIGHT);
    }

    public static RefreshBusRoutesResult SUCCESS(){
        return new RefreshBusRoutesResult(REQUEST_STATUS.SUCCESS);
    }

    public static RefreshBusRoutesResult EMPTY_LIST(){
        return new RefreshBusRoutesResult(REQUEST_STATUS.EMPTY_LIST);
    }

    public static RefreshBusRoutesResult FAILURE(){
        return new RefreshBusRoutesResult(REQUEST_STATUS.NO_DATA);
    }

    public REQUEST_STATUS getRequestStatus() {
        return requestStatus;
    }
}
