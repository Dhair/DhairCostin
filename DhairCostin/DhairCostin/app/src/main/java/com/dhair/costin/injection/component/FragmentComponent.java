package com.dhair.costin.injection.component;

import android.app.Fragment;

import com.dhair.costin.injection.module.FragmentModule;
import com.dhair.costin.injection.scope.FragmentScope;

import dagger.Component;

/**
 * Creator: dengshengjin on 16/1/13 17:15
 * Email: deng.shengjin@zuimeia.com
 */
@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Fragment fragment();
}
