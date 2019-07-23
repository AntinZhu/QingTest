package com.qingqing.test.bean.schedule;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by zhujianxing on 2019/7/22.
 */
public class QingScheduleCheckConfig {
    private final String scheduleCheckKey;
    private final String scheduleCheckName;
    private final QingCronExpression cronExpression;
    private Date nextCheckTime;

    public QingScheduleCheckConfig(String scheduleCheckKey, String scheduleCheckName, String cron) throws ParseException {
        this.scheduleCheckKey = scheduleCheckKey;
        this.scheduleCheckName = scheduleCheckName;
        cronExpression = new QingCronExpression(cron);
        resetNextCheckTime();
    }

    public String getScheduleCheckKey() {
        return scheduleCheckKey;
    }

    public String getScheduleCheckName() {
        return scheduleCheckName;
    }

    public Date getNextScheduleCheckTimeAfter(Date date){
        return cronExpression.getNextValidTimeAfter(date);
    }

    public Date getNextCheckTime() {
        return nextCheckTime;
    }

    public void resetNextCheckTime() {
        // 下一次检查时间
        Date nextScheduleCheckTime = getNextScheduleCheckTimeAfter(new Date());

        // 延后30秒检查
        this.nextCheckTime = new Date(nextScheduleCheckTime.getTime() + 30 * 1000);
    }

    @Override
    public String toString() {
        return "QingScheduleCheckConfig{" +
                "scheduleCheckKey='" + scheduleCheckKey + '\'' +
                ", scheduleCheckName='" + scheduleCheckName + '\'' +
                ", cronExpression=" + cronExpression.getCronExpression() +
                '}';
    }
}
