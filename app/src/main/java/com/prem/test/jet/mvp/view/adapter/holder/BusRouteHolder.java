package com.prem.test.jet.mvp.view.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prem.test.jet.R;
import com.prem.test.jet.mvp.view.custom.BusRouteDetailView;
import com.prem.test.jet.utils.annotation.Font;
import com.prem.test.jet.utils.annotation.processor.FontInjector;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by prem on 01/03/2018.
 */

public class BusRouteHolder extends RecyclerView.ViewHolder {

    public RelativeLayout rlWrapperItem;
    public CircleImageView ivRouteIcon;
    @Font(Font.Fonts.BOOK) public TextView tvRouteName;
    public String idBusRoute;
    public ImageView ivAccessibleIcon;
    @Font(Font.Fonts.LIGHT) public TextView tvRouteDescription;

    public BusRouteHolder(BusRouteDetailView itemView, Context context) {
        super(itemView);
        ivRouteIcon = itemView.findViewById(R.id.ivRouteIcon);
        tvRouteName = itemView.findViewById(R.id.tvRouteName);
        ivAccessibleIcon = itemView.findViewById(R.id.ivAccessibleIcon);
        tvRouteDescription = itemView.findViewById(R.id.tvRouteDescription);
        rlWrapperItem = itemView.findViewById(R.id.rlWrapperItem);

        FontInjector.injectFont(this, context);

    }



}
