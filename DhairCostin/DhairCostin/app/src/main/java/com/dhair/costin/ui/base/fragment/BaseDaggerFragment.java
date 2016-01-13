package com.dhair.costin.ui.base.fragment;

import android.app.Fragment;

/**
 * Creator: dengshengjin on 16/1/13 14:58
 * Email: deng.shengjin@zuimeia.com
 */
public abstract class BaseDaggerFragment extends Fragment {

    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HashComponent<C>) getActivity()).getComponent());
    }
}
