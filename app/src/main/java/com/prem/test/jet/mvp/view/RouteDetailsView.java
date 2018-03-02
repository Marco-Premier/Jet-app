package com.prem.test.jet.mvp.view;

import com.prem.test.jet.mvp.model.realm.BusStop;

import io.realm.OrderedRealmCollection;

/**
 * Created by prem on 01/03/2018.
 */

public interface RouteDetailsView extends BaseView {

    void refreshListData(OrderedRealmCollection<BusStop> busStopList);
    void showAccessibleIcon();
    void hideAccessibleIcon();

}
