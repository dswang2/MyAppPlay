package com.dbstar.myappplay.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by wh on 2017/8/24.
 */

@Scope
@Documented
@Retention(RUNTIME)
public @interface FramentScope {
}
