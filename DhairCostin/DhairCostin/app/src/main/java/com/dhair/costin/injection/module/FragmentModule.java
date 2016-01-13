package com.dhair.costin.injection.module;

import android.app.Fragment;

import com.dhair.costin.injection.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Creator: dengshengjin on 16/1/13 17:15
 * Email: deng.shengjin@zuimeia.com
 */
@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @FragmentScope
    Fragment providerFragment() {//生命周期与FragmentComponent 一致
        return mFragment;
    }
}
