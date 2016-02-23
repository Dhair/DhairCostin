package com.dhair.costin.ui.widget.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Creator: dengshengjin on 16/1/10 17:51
 * Email: deng.shengjin@zuimeia.com
 */
public class LinearRecyclerView extends RecyclerView{
    public LinearRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
    }
}
