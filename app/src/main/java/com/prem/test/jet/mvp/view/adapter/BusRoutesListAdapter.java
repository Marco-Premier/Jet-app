package com.prem.test.jet.mvp.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.prem.test.jet.R;
import com.prem.test.jet.mvp.model.realm.BusRoute;
import com.prem.test.jet.mvp.view.adapter.holder.BusRouteHolder;
import com.prem.test.jet.mvp.view.custom.BusRouteDetailView;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by prem on 01/03/2018.
 */

public class BusRoutesListAdapter extends RealmRecyclerViewAdapter<BusRoute,BusRouteHolder> {


    private final View.OnClickListener onItemClick;
    private List<BusRoute> busRouteList;
    private final Context context;

    public BusRoutesListAdapter(OrderedRealmCollection<BusRoute> busRouteList, View.OnClickListener onItemClick, Context context){
        super(busRouteList,true);

        this.busRouteList = busRouteList;
        this.onItemClick = onItemClick;
        this.context = context;
    }

    public void refreshBusRoutesList(OrderedRealmCollection<BusRoute> busRouteList){
        this.busRouteList = busRouteList;
        notifyDataSetChanged();
    }

    @Override
    public BusRouteHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        //Let's use our custom view
        BusRouteDetailView view = new BusRouteDetailView(context);
        view.setOnClickListener(onItemClick);
        return view.getBusRouteHolder();

    }

    @Override
    public void onBindViewHolder(BusRouteHolder busRouteHolder, int position) {

        final BusRoute busRoute = this.busRouteList.get(position);
        busRouteHolder.tvRouteName.setText(busRoute.getName());
        busRouteHolder.idBusRoute = busRoute.getIdBusRoutes();
        busRouteHolder.tvRouteDescription.setText(busRoute.getDescription());
        Picasso.with(context).load(busRoute.getImageUrl()).error(R.drawable.bus_default_icon).into(busRouteHolder.ivRouteIcon);

    }

    @Override
    public int getItemCount() {
        int count = (null == busRouteList) ? 0 : busRouteList.size();
        return count;
    }
}

