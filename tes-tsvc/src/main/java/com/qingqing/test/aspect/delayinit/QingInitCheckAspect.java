package com.qingqing.test.aspect.delayinit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by zhujianxing on 2018/10/15.
 */
@Aspect
@Component
public class QingInitCheckAspect {
    private static final Logger logger = LoggerFactory.getLogger(QingInitCheckAspect.class);

    @Before(value = "@annotation(com.qingqing.test.aspect.delayinit.QingInitCheck)")
    public void beforeInitCheck(JoinPoint joinPoint) {
        logger.info(joinPoint.getTarget().getClass().getName());
        if(joinPoint.getTarget() instanceof IQingInitDelayable){
            IQingInitDelayable delayInit = (IQingInitDelayable) joinPoint.getTarget();
            if(delayInit.isNeedInit()){
                delayInit.doInit();
            }
        }
    }
}
