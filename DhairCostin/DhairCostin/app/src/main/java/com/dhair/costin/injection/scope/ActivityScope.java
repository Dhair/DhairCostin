package com.dhair.costin.injection.scope;

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
public @interface ActivityScope {//局部单例,Scope其实就是一个Component的对象，Scope的注解就是在Component上的
}
