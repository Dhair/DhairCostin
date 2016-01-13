package com.dhair.costin.injection.component;

import com.dhair.costin.data.model.UserModel;
import com.dhair.costin.injection.module.UserModule;
import com.dhair.costin.injection.scope.UserScope;

import dagger.Component;

/**
 * Creator: dengshengjin on 16/1/13 17:14
 * Email: deng.shengjin@zuimeia.com
 */
@UserScope
@Component(dependencies = ApplicationComponent.class,modules = UserModule.class)
public interface UserComponent {
    UserModel userModel();
}
