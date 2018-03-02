package com.prem.test.jet.mvp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prem.test.jet.R;
import com.prem.test.jet.mvp.model.realm.BusStop;
import com.prem.test.jet.mvp.view.adapter.holder.BusStopHolder;

import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by prem on 01/03/2018.
 */


public class BusStopListAdapter extends RealmRecyclerViewAdapter<BusStop,BusStopHolder> {


    private List<BusStop> busStopList;
    private final Context context;

    public BusStopListAdapter(OrderedRealmCollection<BusStop> busStopList, Context context){
        super(busStopList,true);

        this.busStopList = busStopList;
        this.context = context;
    }

    public void refreshBusRoutesList(OrderedRealmCollection<BusStop> busStopList){
        this.busStopList = busStopList;
        notifyDataSetChanged();
    }

    @Override
    public BusStopHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.bus_stop_list_item, viewGroup, false);

        return new BusStopHolder(v,context);
    }

    @Override
    public void onBindViewHolder(BusStopHolder busStopHolder, int position) {

        final BusStop busStop= this.busStopList.get(position);
        busStopHolder.tvStopName.setText(busStop.getStopName());

    }

    @Override
    public int getItemCount() {
        int count = (null == busStopList) ? 0 : busStopList.size();
        return count;
    }
}