package com.qingqing.test.aspect.datasource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhujianxing on 2017/10/9.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface ErrorCodeDataSource {
     int errorCode() default 1001;
     String msg() default "";
     String hintMessage();

     String groupKey() default "default";
}
