package com.qingqing.test.controller;

import com.qingqing.test.manager.CommonSyncManager;
import com.qingqing.test.manager.ISyncable.SyncType;
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
public class CronController {

    @Autowired
    private CommonSyncManager commonSyncManager;

    @RequestMapping("sync")
    @ResponseBody
    public ResponseEntity<String> sync(@RequestParam("type") SyncType syncType){
        commonSyncManager.sync(syncType);

        return ResponseEntity.ok("ok");
    }
}
