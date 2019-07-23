package com.qingqing.test.manager;

import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.util.TimeUtil;
import com.qingqing.common.zookeeper.Leader;
import com.qingqing.test.bean.schedule.QingScheduleCheck;
import com.qingqing.test.bean.schedule.QingScheduleCheckConfig;
import com.qingqing.test.bean.schedule.QingScheduleCheckable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhujianxing on 2019/7/22.
 */
@Component
public class QingScheduleCheckManager extends Leader {

    private static final Logger logger = LoggerFactory.getLogger(QingScheduleCheckManager.class);

    private Map<String, String> redisMap = new HashMap<>();
    private Map<String, QingScheduleCheckConfig> scheduleCheckConfigMap = new ConcurrentHashMap<>();

    @Autowired
    private List<QingScheduleCheckable> checkBean;

    @PostConstruct
    public void init(){
        for (QingScheduleCheckable qingScheduleCheckable : checkBean) {
            Method[] methods = qingScheduleCheckable.getClass().getDeclaredMethods();
            for (Method method : methods) {
                QingScheduleCheck qingScheduleCheck = AnnotationUtils.findAnnotation(method, QingScheduleCheck.class);
                if(qingScheduleCheck != null){
                    registerScheduleCheck(qingScheduleCheck.scheduleCheckKey(), qingScheduleCheck.scheduleCheckName(), qingScheduleCheck.checkCron());
                }
            }
        }
    }

    @Override
    public String getResourcePath() {
        return "leader/schedule/check";
    }

    @Override
    public void execute() {
        Date now = new Date();
        for(QingScheduleCheckConfig config : scheduleCheckConfigMap.values()){
            if(config.getNextCheckTime() != null && config.getNextCheckTime().before(now)){
                String markRunning = redisMap.get(getCacheKey(config.getScheduleCheckKey()));
                if(markRunning == null){
                    logger.error("find schedule check fail, name:" + config.getScheduleCheckName());
                }

                config.resetNextCheckTime();
            }
        }
    }

    public void registerScheduleCheck(String scheduleCheckKey, String scheduleCheckName, String checkCron){
        QingScheduleCheckConfig config;
        try {
            config = new QingScheduleCheckConfig(scheduleCheckKey, scheduleCheckName, checkCron);
            scheduleCheckConfigMap.put(scheduleCheckKey, config);
        } catch (ParseException e) {
            throw new QingQingRuntimeException("registerScheduleCheck fail for key:" + scheduleCheckKey, e);
        }
    }

    public void markRunning(String scheduleCheckKey){
        QingScheduleCheckConfig qingScheduleCheckConfig = scheduleCheckConfigMap.get(scheduleCheckKey);
        if(qingScheduleCheckConfig == null){
            logger.error("unknown scheduleCheckKey for value:" + scheduleCheckKey);
            return;
        }

        try{
            redisMap.put(getCacheKey(scheduleCheckKey), TimeUtil.dateToString(new Date(), TimeUtil.TIME_IN_DETAIL_STRING_FORMAT));
            logger.info("cache second" +  getCacheSeconds(qingScheduleCheckConfig));
        }catch(Exception e){
            logger.error("schedule check mark running fail, scheduleCheckKey:" + scheduleCheckKey);
        }
    }

    private Long getCacheSeconds(QingScheduleCheckConfig qingScheduleCheckConfig){
        Date now = new Date();
        Date nextCheckTime = qingScheduleCheckConfig.getNextScheduleCheckTimeAfter(now);

        return (nextCheckTime.getTime() - now.getTime()) / 1000;
    }

    private String getCacheKey(String key){
        return "api:schedule:check:" + key;
    }
}
