package com.prem.test.jet.mvp.view.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.prem.test.jet.R;
import com.prem.test.jet.utils.annotation.Font;
import com.prem.test.jet.utils.annotation.processor.FontInjector;

/**
 * Created by prem on 01/03/2018.
 */

public class BusStopHolder extends RecyclerView.ViewHolder {

    @Font(Font.Fonts.ROMAN) public TextView tvStopName;

    public BusStopHolder(View itemView, Context context) {
        super(itemView);
        tvStopName = itemView.findViewById(R.id.tvStopName);
        FontInjector.injectFont(this, context);
    }

}
