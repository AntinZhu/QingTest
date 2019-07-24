package com.qingqing.test.controller;

import com.qingqing.common.domain.AsyncEventType;
import com.qingqing.test.bean.schedule.QingScheduleCheck;
import com.qingqing.test.bean.schedule.QingScheduleCheckable;
import com.qingqing.test.manager.CommonSyncManager;
import com.qingqing.test.manager.ISyncable.SyncType;
import com.qingqing.test.manager.QingScheduleCheckManager;
import com.qingqing.test.rabbit.AsyncEventRabbitMsgListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhujianxing on 2019/5/28.
 */
@Controller
@RequestMapping("/cron/v1")
public class CronController implements QingScheduleCheckable {

    @Autowired
    private CommonSyncManager commonSyncManager;
    @Autowired
    private QingScheduleCheckManager qingScheduleCheckManager;

    @RequestMapping("sync")
    @ResponseBody
    @QingScheduleCheck(scheduleCheckKey = "sync", scheduleCheckName = "配置同步", checkCron = "0/10 * * * * ?")
    public ResponseEntity<String> sync(@RequestParam("type") SyncType syncType){
        commonSyncManager.sync(syncType);

        return ResponseEntity.ok("ok");
    }

    @RequestMapping("event/ignore")
    @ResponseBody
    public ResponseEntity<String> ignoreEvent(@RequestParam("eventType") int eventType){
        AsyncEventRabbitMsgListener.addIgnore(AsyncEventType.valueOf(eventType));

        return ResponseEntity.ok("ok");
    }

    @RequestMapping("event/resume")
    @ResponseBody
    public ResponseEntity<String> resumeEvent(@RequestParam("eventType") int eventType){
        AsyncEventRabbitMsgListener.removeIgnore(AsyncEventType.valueOf(eventType));

        return ResponseEntity.ok("ok");
    }

    @RequestMapping("check")
    @ResponseBody
    public ResponseEntity<String> check(){
        qingScheduleCheckManager.execute();

        return ResponseEntity.ok("ok");
    }


}
