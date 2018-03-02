package com.prem.test.jet.mvp.view.controller;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.prem.test.jet.R;
import com.prem.test.jet.mvp.model.realm.BusRoute;
import com.prem.test.jet.mvp.presenter.RoutesListPresenter;
import com.prem.test.jet.mvp.view.RoutesListView;
import com.prem.test.jet.mvp.view.adapter.BusRoutesListAdapter;
import com.prem.test.jet.mvp.view.controller.contract.BaseController;
import com.prem.test.jet.mvp.view.custom.BusRouteDetailView;
import com.prem.test.jet.utils.annotation.Font;
import com.prem.test.jet.utils.annotation.processor.FontInjector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.OrderedRealmCollection;

/**
 * Created by prem on 28/02/2018.
 */

public class RoutesListController extends BaseController<RoutesListView,RoutesListPresenter> implements RoutesListView {

    @BindView(R.id.rvRoutesList) RecyclerView rvRoutesList;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @Font(Font.Fonts.ROMAN) @BindView(R.id.tvInternetError) TextView tvInternetError;
    @Font(Font.Fonts.ROMAN) @BindView(R.id.tvEmptyList) TextView tvEmptyList;
    @BindView(R.id.pbLoader) ProgressBar pbLoader;

    private Unbinder unbinder;
    private View.OnClickListener itemListener;
    private BusRoutesListAdapter busRoutesListAdapter;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View rootView = inflater.inflate(R.layout.routes_list_layout, container, false);

        unbinder = ButterKnife.bind(this, rootView);
        FontInjector.injectFont(this,getActivity());
        setupToolbar(toolbar,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.rlc_title);
        setHasOptionsMenu(true);

        itemListener = view -> {
            //Swap controller and display BusRouteDetailsController
            RouteDetailsController routeDetailsController = new RouteDetailsController((BusRouteDetailView)view);
            routeDetailsController.setRetainViewMode(Controller.RetainViewMode.RETAIN_DETACH);
            getRouter().pushController(
                    RouterTransaction.with(routeDetailsController)
                            .pushChangeHandler(new HorizontalChangeHandler(DEFAULT_PUSH_TRANSITION_DURATION))
                            .popChangeHandler(new HorizontalChangeHandler(DEFAULT_PUSH_TRANSITION_DURATION)));
        };

        //setup recycler view
        rvRoutesList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvRoutesList.setLayoutManager(llm);

        rvRoutesList.addItemDecoration(
                new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        busRoutesListAdapter = new BusRoutesListAdapter(null,
                itemListener,
                getActivity());
        rvRoutesList.setAdapter(busRoutesListAdapter);

        return rootView;
    }

    @NonNull
    @Override
    public RoutesListPresenter createPresenter() {
        return new RoutesListPresenter();
    }

    @Override
    protected void onDestroyView(View view) {
        unbinder.unbind();
    }

    @Override
    public void refreshListData(OrderedRealmCollection<BusRoute> busRouteList) {
        busRoutesListAdapter.refreshBusRoutesList(busRouteList);
    }

    @Override
    public void hideNotificationViews() {
        pbLoader.setVisibility(View.GONE);
        tvEmptyList.setVisibility(View.GONE);
        tvInternetError.setVisibility(View.GONE);
        rvRoutesList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingView() {

        rvRoutesList.setVisibility(View.GONE);
        pbLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void showInternetErrorView() {
        rvRoutesList.setVisibility(View.GONE);
        tvInternetError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyListView() {
        rvRoutesList.setVisibility(View.GONE);
        tvEmptyList.setVisibility(View.VISIBLE);
    }
}
