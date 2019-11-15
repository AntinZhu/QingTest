package com.qingqing.test.aspect.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhujianxing on 2019/10/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface IpLoginValid {

    IpLoginValidType validaType() default IpLoginValidType.all;

    String assignIp() default "";
}
