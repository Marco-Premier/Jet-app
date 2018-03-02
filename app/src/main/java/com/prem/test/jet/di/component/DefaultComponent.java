package com.prem.test.jet.di.component;

import android.content.Context;

import com.prem.test.jet.di.module.ApplicationModule;
import com.prem.test.jet.di.module.LooperModule;
import com.prem.test.jet.di.module.ModelModule;
import com.prem.test.jet.di.module.NetworkModule;
import com.prem.test.jet.di.module.StateModule;
import com.prem.test.jet.mvp.presenter.RouteDetailsPresenter;
import com.prem.test.jet.mvp.presenter.RoutesListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by prem on 28/02/2018.
 */

@Component(modules={ApplicationModule.class, StateModule.class, LooperModule.class, NetworkModule.class, ModelModule.class})
@Singleton
public interface DefaultComponent {

    Context context();

    void inject(RoutesListPresenter routesListPresenter);
    void inject(RouteDetailsPresenter routeDetailsPresenter);

}
