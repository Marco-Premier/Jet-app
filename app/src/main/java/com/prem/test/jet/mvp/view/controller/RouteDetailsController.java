package com.prem.test.jet.mvp.view.controller;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.prem.test.jet.R;
import com.prem.test.jet.mvp.model.realm.BusStop;
import com.prem.test.jet.mvp.presenter.RouteDetailsPresenter;
import com.prem.test.jet.mvp.view.RouteDetailsView;
import com.prem.test.jet.mvp.view.adapter.BusStopListAdapter;
import com.prem.test.jet.mvp.view.adapter.decorator.CustomItemDecorator;
import com.prem.test.jet.mvp.view.controller.contract.BaseController;
import com.prem.test.jet.mvp.view.custom.BusRouteDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.OrderedRealmCollection;

/**
 * Created by prem on 01/03/2018.
 */

public class RouteDetailsController extends BaseController<RouteDetailsView,RouteDetailsPresenter> implements RouteDetailsView {

    @BindView(R.id.rvStopsList) RecyclerView rvStopsList;
    @BindView(R.id.flWrapperCustomView) FrameLayout flWrapperCustomView;

    private Unbinder unbinder;
    private BusStopListAdapter busStopListAdapter;
    //Shared view
    private BusRouteDetailView busRouteDetailView;

    public RouteDetailsController(){}

    public RouteDetailsController(BusRouteDetailView busRouteDetailView){
        this.busRouteDetailView = busRouteDetailView;
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View rootView = inflater.inflate(R.layout.route_details_layout, container, false);

        unbinder = ButterKnife.bind(this, rootView);

        adaptCustomView();

        //Setup custom item decorator to display vertical line among list items
        CustomItemDecorator customItemDecorator = new CustomItemDecorator(getActivity());
        rvStopsList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvStopsList.setLayoutManager(llm);
        rvStopsList.addItemDecoration(customItemDecorator);

        busStopListAdapter = new BusStopListAdapter(null, getActivity());
        rvStopsList.setAdapter(busStopListAdapter);

        return rootView;
    }

    private void adaptCustomView(){
        ((ViewGroup)busRouteDetailView.getParent()).removeView(busRouteDetailView);
        flWrapperCustomView.addView(busRouteDetailView);
        busRouteDetailView.getBusRouteHolder().tvRouteDescription.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)  busRouteDetailView.getBusRouteHolder().tvRouteName.getLayoutParams();
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL,0);
        busRouteDetailView.getBusRouteHolder().tvRouteName.setLayoutParams(layoutParams);
        busRouteDetailView.getBusRouteHolder().rlWrapperItem.setBackgroundColor(Color.WHITE);
    }

    @NonNull
    @Override
    public RouteDetailsPresenter createPresenter() {
        return new RouteDetailsPresenter(busRouteDetailView.getBusRouteHolder().idBusRoute);
    }

    @Override
    protected void onDestroyView(View view) {
        unbinder.unbind();
    }


    @Override
    public void refreshListData(OrderedRealmCollection<BusStop> busStopList) {
        busStopListAdapter.refreshBusRoutesList(busStopList);
    }

    @Override
    public void showAccessibleIcon() {
        busRouteDetailView.getBusRouteHolder().ivAccessibleIcon.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideAccessibleIcon() {
        busRouteDetailView.getBusRouteHolder().ivAccessibleIcon.setVisibility(View.GONE);
    }

}
