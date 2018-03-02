package com.prem.test.jet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.prem.test.jet.mvp.view.controller.RoutesListController;

/**
 * Created by prem on 28/02/2018.
 */

public class MainActivity extends AppCompatActivity {

    private Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_layout);

        ViewGroup container = findViewById(R.id.wrapperController);

        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            RoutesListController routesListController = new RoutesListController();
            routesListController.setRetainViewMode(Controller.RetainViewMode.RETAIN_DETACH);
            router.setRoot(RouterTransaction.with(routesListController));
        }
    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }

}
