package com.qingqing.test.spring.aspect;

import com.qingqing.test.bean.schedule.QingScheduleCheck;
import com.qingqing.test.manager.QingScheduleCheckManager;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhujianxing on 2018/7/16.
 */
@Aspect
@Component
public class QingScheduleCheckAspect {

    private static final Logger logger = LoggerFactory.getLogger(QingScheduleCheckAspect.class);

    @Autowired
    private QingScheduleCheckManager qingScheduleCheckManager;

    @Before(value = "@annotation(scheduleCheck)")
    public void before(QingScheduleCheck scheduleCheck) throws Throwable{
        logger.info("before QingScheduleCheck for : " + scheduleCheck.scheduleCheckKey());
        qingScheduleCheckManager.markRunning(scheduleCheck.scheduleCheckKey());
    }
}
