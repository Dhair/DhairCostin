package com.dhair.autolayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Creator: dengshengjin on 16/1/9 17:38
 * Email: deng.shengjin@zuimeia.com
 */
public interface ViewCreator {
    View create(String name, Context context, AttributeSet attrs);
}
