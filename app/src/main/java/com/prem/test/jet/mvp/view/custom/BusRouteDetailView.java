package com.prem.test.jet.mvp.view.custom;

import android.content.Context;
import android.widget.RelativeLayout;

import com.prem.test.jet.R;
import com.prem.test.jet.mvp.view.adapter.holder.BusRouteHolder;

/**
 * Created by prem on 02/03/2018.
 */

public class BusRouteDetailView extends RelativeLayout{

    private final BusRouteHolder busRouteHolder;

    public BusRouteDetailView(Context context) {
        super(context);

        inflate(context, R.layout.bus_routes_list_item  , this);
        busRouteHolder = new BusRouteHolder(this, context);

    }

    public BusRouteHolder getBusRouteHolder() {
        return busRouteHolder;
    }

}
