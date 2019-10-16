package com.qingqing.test.aspect.mock;

import com.qingqing.test.util.ExpressionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by zhujianxing on 2019/10/16.
 */
@Component
@Aspect
public class QingMockAspect {

    private static final Logger logger = LoggerFactory.getLogger(QingMockAspect.class);

    @Around(value = "@annotation(qingMock)", argNames = "qingMock")
    public Object around(final ProceedingJoinPoint pjp, QingMock qingMock) throws Throwable {
        // TODO 判断mock开关
        logger.info("switchKey:" + qingMock.mockEnableSwitchKey());
        logger.info("mockType:" + qingMock.type());
        logger.info("ruleParamExpression:" + qingMock.ruleParamExpression());
        logger.info("param:" + ExpressionUtils.getValue(pjp.getArgs()[qingMock.ruleParamIndex()], qingMock.ruleParamExpression()));
        // TODO 无参数
        // TODO 无需参数

        return "mock";
    }
}
