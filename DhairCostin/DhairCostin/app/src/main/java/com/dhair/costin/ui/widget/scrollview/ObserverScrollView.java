package com.dhair.costin.ui.widget.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Creator: dengshengjin on 16/1/29 13:17
 * Email: deng.shengjin@zuimeia.com
 */
public class ObserverScrollView extends ScrollView {
    public ObserverScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public interface OnScrollListener {
        void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt);
    }
}
