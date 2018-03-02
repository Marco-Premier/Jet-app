package com.prem.test.jet.mvp.view.controller.contract;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController;
import com.prem.test.jet.mvp.view.BaseView;

/**
 * Created by prem on 01/03/2018.
 */

public abstract class BaseController<V extends MvpView, T extends MvpPresenter<V>> extends MvpController<V,T> implements BaseView {

    protected final static int DEFAULT_PUSH_TRANSITION_DURATION = 220;

    protected void setupToolbar(Toolbar toolbar, boolean showHomeButton){
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        if(showHomeButton)
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
