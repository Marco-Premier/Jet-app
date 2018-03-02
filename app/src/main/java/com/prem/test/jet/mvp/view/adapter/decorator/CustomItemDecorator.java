package com.prem.test.jet.mvp.view.adapter.decorator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.prem.test.jet.R;

/**
 * Created by prem on 01/03/2018.
 */

public class CustomItemDecorator extends RecyclerView.ItemDecoration {

    private final Drawable mDivider;

    public CustomItemDecorator(Context context) {
        mDivider = context.getResources().getDrawable(R.drawable.line);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View child = parent.getChildAt(i);
            int left = child.getPaddingLeft();
            RelativeLayout rl = child.findViewById(R.id.rlWrapperItem);
            View iconView = rl.getChildAt(0);
            int w = iconView.getWidth();

            int top = child.getBottom();
            left += (w / 2) - 9;

            mDivider.setBounds(left, top - 5, left + 18, top + 140 + 5);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.bottom = 140;
    }
}
