package com.dhair.costin.ui.base.fragment;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;

/**
 * Creator: dengshengjin on 16/1/13 14:58
 * Email: deng.shengjin@zuimeia.com
 */
public abstract class BaseDaggerFragment extends Fragment {
    private Handler mHandler;

    protected Handler getHandler() {
        if (mHandler == null) {
            synchronized (this) {
                if (mHandler == null) {
                    mHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        return mHandler;
    }

    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HashComponent<C>) getActivity()).getComponent());
    }

    protected boolean isFinishing() {
        return getActivity() == null || getActivity().isFinishing();
    }
}
