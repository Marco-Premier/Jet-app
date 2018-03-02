package com.prem.test.jet;

import android.app.Application;

import com.prem.test.jet.di.component.DaggerDefaultComponent;
import com.prem.test.jet.di.component.DefaultComponent;
import com.prem.test.jet.di.module.ApplicationModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by prem on 28/02/2018.
 */

public class Jet extends Application {

    private static DefaultComponent defaultComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(getApplicationContext());
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfig);

        defaultComponent = DaggerDefaultComponent.builder().applicationModule(new ApplicationModule(getApplicationContext())).build();

    }

    public static DefaultComponent getDefaultComponent(){
        return defaultComponent;
    }

}
