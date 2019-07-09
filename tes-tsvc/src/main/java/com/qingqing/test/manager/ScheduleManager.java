package com.qingqing.test.manager;

import com.google.common.collect.Maps;
import com.qingqing.common.util.CollectionsUtil;
import com.qingqing.test.bean.common.CommonLink;
import com.qingqing.test.bean.schedule.QingScheduleType;
import com.qingqing.test.bean.schedule.QingScheduleable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujianxing on 2019/7/9.
 */
@Component
public class ScheduleManager {

    @Autowired
    private List<QingScheduleable> qingScheduleableList;

    private Map<QingScheduleType, CommonLink<QingScheduleable>> scheduleTypeCommonLinkMap;

    @PostConstruct
    public void  init(){
        if(!CollectionsUtil.isNullOrEmpty(qingScheduleableList)){
            Map<QingScheduleType, CommonLink<QingScheduleable>> tmpQingSyncMap = Maps.newHashMapWithExpectedSize(qingScheduleableList.size());
            for (QingScheduleable qingScheduleable : qingScheduleableList) {
                for (QingScheduleType qingSyncType : qingScheduleable.getScheduleTypes()) {
                    CommonLink newQingSyncAbleLink = new CommonLink(qingScheduleable, null);
                    CommonLink oldQingSyncAbleLink = tmpQingSyncMap.put(qingSyncType, newQingSyncAbleLink);
                    newQingSyncAbleLink.setNext(oldQingSyncAbleLink);
                }
            }

            scheduleTypeCommonLinkMap = tmpQingSyncMap;
        }else{
            scheduleTypeCommonLinkMap = Collections.emptyMap();
        }
    }

    @Scheduled(cron = "0 0 9 * * *")
    public void reportTotalUse(){
        CommonLink<QingScheduleable> dailyScheduleable = scheduleTypeCommonLinkMap.get(QingScheduleType.daily);
        do{
            dailyScheduleable.getT().doSchedule();
            dailyScheduleable = dailyScheduleable.getNext();
        }while(dailyScheduleable != null);
    }
}
