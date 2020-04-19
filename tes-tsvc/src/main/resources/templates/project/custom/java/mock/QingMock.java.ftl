package ${basePackage}.aspect.mock;

import java.lang.annotation.*;

/**
 * Created by ${user!'test-api'} on ${date}
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
    QingMockType type();

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
