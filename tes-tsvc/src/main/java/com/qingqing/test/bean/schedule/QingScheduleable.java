package com.qingqing.test.bean.schedule;

/**
 * Created by zhujianxing on 2019/7/9.
 */
public interface QingScheduleable {

    void doSchedule();

    QingScheduleType[] getScheduleTypes();
}
