package com.qingqing.test.aspect.qingswitch.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhujianxing on 2019/3/7.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface QingSwitch {

    String key() default "";

    boolean defaultValue() default true;

    String hitMessage() default "";

    int errorCode() default 54321;
}
