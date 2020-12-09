package com.qingqing.test.aspect.mock;

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
public @interface QingMock {
    String enableKey() default "";

    /**
     *
     * @return
     */
    String type();

    /**
     * 参数下标
     * @return
     */
    int ruleParamIndex() default -1;

    /**
     * 参数表达式
     * @return
     */
    String ruleParamExpression()  default "";

    /**
     * 不为空时，固定结果，不走mock服务
     * @return
     */
    String fixedResult() default "";
}
