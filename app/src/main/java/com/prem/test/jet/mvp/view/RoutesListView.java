package com.prem.test.jet.mvp.view;

import com.prem.test.jet.mvp.model.realm.BusRoute;

import io.realm.OrderedRealmCollection;

/**
 * Created by prem on 28/02/2018.
 */

public interface RoutesListView extends BaseView {

    void refreshListData(OrderedRealmCollection<BusRoute> busRouteList);
    void hideNotificationViews();
    void showLoadingView();
    void showInternetErrorView();
    void showEmptyListView();

}
