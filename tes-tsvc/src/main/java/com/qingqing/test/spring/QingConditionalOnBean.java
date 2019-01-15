package com.qingqing.test.spring;

import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhujianxing on 2019/1/11.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional({QingOnAllBeanCondition.class})
public @interface QingConditionalOnBean {
    Class<?>[] value();

    String[] name() default {};

    SearchStrategy search() default SearchStrategy.ALL;
}
