package com.prem.test.jet.mvp.view.state;

import com.prem.test.jet.mvp.view.state.common.REQUEST_STATUS;
import com.prem.test.jet.mvp.view.state.contract.BaseState;

/**
 * Created by prem on 28/02/2018.
 */

public class RefreshBusRoutesState implements BaseState {

    private REQUEST_STATUS requestStatus;
    private boolean isWaitingForData;

    public RefreshBusRoutesState() {
        requestStatus = REQUEST_STATUS.IDLE;
    }

    public boolean isWaitingForData() {
        return isWaitingForData;
    }

    public void setWaitingForData(boolean waitingForData) {
        isWaitingForData = waitingForData;
    }

    public REQUEST_STATUS getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(REQUEST_STATUS requestStatus) {
        this.requestStatus = requestStatus;
    }
}
