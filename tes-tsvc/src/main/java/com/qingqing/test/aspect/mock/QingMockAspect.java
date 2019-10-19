package com.qingqing.test.aspect.mock;

import com.qingqing.common.util.JsonUtil;
import com.qingqing.test.client.ApiTestClient;
import com.qingqing.test.util.ExpressionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by zhujianxing on 2019/10/16.
 */
@Aspect
public class QingMockAspect {

    private static final Logger logger = LoggerFactory.getLogger(QingMockAspect.class);

    @Autowired
    private ApiTestClient apiTestClient;

    @Around(value = "@annotation(qingMock)", argNames = "qingMock")
    public Object around(final ProceedingJoinPoint pjp, QingMock qingMock) throws Throwable {
        // TODO 判断mock开关
        logger.info("switchKey:" + qingMock.mockEnableSwitchKey());
        logger.info("mockType:" + qingMock.type());
        logger.info("ruleParamExpression:" + qingMock.ruleParamExpression());
        logger.info("param:" + ExpressionUtils.getValue(pjp.getArgs()[qingMock.ruleParamIndex()], qingMock.ruleParamExpression()));
        // TODO 无参数
        // TODO 无需参数
        String mockResult = apiTestClient.mock(qingMock.type().name(), String.valueOf(ExpressionUtils.getValue(pjp.getArgs()[qingMock.ruleParamIndex()], qingMock.ruleParamExpression())));

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        logger.info("return:" + method.getReturnType().getName());
        logger.info("isVoid:" + Void.class.equals(method.getReturnType()));
        logger.info("isString:" + String.class.equals(method.getReturnType()));
        logger.info("---------------------------------------");

        if(method.getReturnType().isEnum()){
            return Enum.valueOf((Class<Enum>)method.getReturnType(), mockResult);
        }

        if(String.class.equals(method.getReturnType())){
            return mockResult;
        }else {
            return JsonUtil.getObjectFromJson(mockResult, method.getReturnType());
        }

//        return pjp.proceed();
    }
}
