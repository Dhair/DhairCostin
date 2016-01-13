package com.dhair.costin.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Creator: dengshengjin on 16/1/12 20:56
 * Email: deng.shengjin@zuimeia.com
 * 限制作用域
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
